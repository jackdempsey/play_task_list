package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task
import com.typesafe.plugin._
object Application extends Controller {
  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }

  def mailJack = {

    val mail = use[MailerPlugin].email
    mail.setSubject("mailer")
    mail.setRecipient("Peter Hausel Junior <noreply@email.com>","example@foo.com")
    //or use a list
    mail.setBcc(List("Dummy <example@example.org>", "Dummy2 <example@example.org>"):_*)
    mail.setFrom("Peter Hausel <noreply@email.com>")
    //sends html
    mail.sendHtml("<html>html</html>" )
    //sends text/text
    mail.send( "text" )
    //sends both text and html
    mail.send( "text", "<html>html</html>")
  }




}
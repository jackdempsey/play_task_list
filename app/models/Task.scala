package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

/**
 * Created by jack on 12/16/13.
 */

case class Task(id: Long, label: String)


object Task {
  val task = {
    get[Long]("id") ~
      get[String]("label") map {
      case id ~ label => Task(id, label)
    }
  }

  def all(): List[Task] = DB.withConnection {
    implicit c =>
      SQL("select * from task").as(task *)
  }

  def create(label: String) {
    DB.withConnection {
      implicit c =>
        SQL("insert into task (label) values ({label})").on(
          'label -> label
        ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection {
      implicit c =>
        SQL("delete from task where id = {id}").on(
          'id -> id
        ).executeUpdate()
    }
  }
}


//scala> import com.typesafe.plugin._
//import com.typesafe.plugin._
//
//scala> val mail = use[MailerPlugin].email
//mail: com.typesafe.plugin.MailerAPI = com.typesafe.plugin.CommonsMailer@1b46116b
//
//scala> mail.setSubject("mailer")
//res3: com.typesafe.plugin.MailerAPI = com.typesafe.plugin.CommonsMailer@1b46116b
//
//scala> mail.setRecipient("jack.dempsey@email.com")
//<console>:18: error: value setRecipient is not a member of com.typesafe.plugin.MailerAPI
//mail.setRecipient("jack.dempsey@email.com")
//^
//
//scala> help
//<console>:17: error: not found: value help
//help
//^
//
//scala> mail.addFrom("jack@freelancing.io")
//res6: com.typesafe.plugin.MailerAPI = com.typesafe.plugin.CommonsMailer@1b46116b
//
//scala> mail.addTo("jack.dempsey@gmail.com")
//<console>:18: error: value addTo is not a member of com.typesafe.plugin.MailerAPI
//mail.addTo("jack.dempsey@gmail.com")
//^
//
//scala> mail.addRecipient("jack.dempsey@gmail.com")
//res8: com.typesafe.plugin.MailerAPI = com.typesafe.plugin.CommonsMailer@1b46116b
//
//scala> mail.sendHtml("<html>html</html>" )


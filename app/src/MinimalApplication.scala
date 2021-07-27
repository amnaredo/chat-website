package app
import scalatags.Text.all._
object MinimalApplication extends cask.MainRoutes{
  var messages = Vector(("alice", "Hello World!"), ("bob", "I am cow, hear me moo"))
  val bootstrap = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.css"

  @cask.get("/")
  def hello() = doctype("html")(
    html(
      head(link(rel := "stylesheet", href := bootstrap)),
      body(
        div(cls := "container")(
          h1("Scala Chat!"),
          div(
            for ((name, msg) <- messages) yield p(b(name), " ", msg)
          ),
          form(action := "/", method := "post")(
            input(`type` := "text", name := "name", placeholder := "User name"),
            input(`type` := "text", name := "msg", placerolder := "Write a message!"),
            input(`tyoe` := "submit") 
          )
        )
      )
    )
  )

  @cask.postForm("/")
  def postChatMsg(name: String, msg: String) = {
    messages = messages :+ (name -> msg)
    hello()
  }

  initialize()
}

package app.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import java.time.DayOfWeek


/**
  * Created by haemin on 7/9/17.
  */
object CourseTable {
  case class Course(
    name: String,
    time: Seq[(DayOfWeek, Seq[Int], String)],
    department: String,
    area: String,
    credit: Int,
    curEnroll: Int,
    maxEnroll: Int,
    note: String
  )

  implicit class DowToString(d: DayOfWeek) {
    def toKorean = d match {
      case DayOfWeek.MONDAY => "월요일"
      case DayOfWeek.TUESDAY => "화요일"
      case DayOfWeek.WEDNESDAY => "수요일"
      case DayOfWeek.THURSDAY => "목요일"
      case DayOfWeek.FRIDAY => "금요일"
      case DayOfWeek.SATURDAY => "토요일"
      case DayOfWeek.SUNDAY => "일요일"
    }
  }

  implicit class CourseDiv(c: Course) {
    def toDiv = <.tr(
      <.td(c.name),
      <.td(
        c.time.map { case (dow, times, room) =>
          <.span(dow.toKorean + " " + times.mkString("", " ", " ") + room)
        }.zipWithIndex.map { case (elem, i) =>
            if (i == 0) elem
            else <.span(<.br, elem)
        }.toTagMod
      ),
      <.td(c.department),
      <.td(c.area),
      <.td(c.credit),
      <.td(c.curEnroll, "/", c.maxEnroll),
      <.td(c.note)
    )
  }

  val courses = Seq(
    Course(
      name = "자료구조",
      time = Seq(
        (DayOfWeek.FRIDAY, Seq(8, 9, 10), "3303"),
        (DayOfWeek.FRIDAY, Seq(8, 9, 10), "3303")
      ),
      department = "융복합소프트웨어",
      area = "이중(부)전공",
      credit = 3,
      curEnroll = 0,
      maxEnroll = 60,
      note = ""
    ),
    Course(
      name = "International Trade Theory",
      time = Seq((DayOfWeek.TUESDAY, Seq(7, 8, 9), "2303")),
      department = "국제통상학과",
      area = "전공",
      credit = 3,
      curEnroll = 0,
      maxEnroll = 60,
      note = "1전공자만수강"
    ),
    Course(
      name = "만화와애니메이션의이해",
      time = Seq((DayOfWeek.THURSDAY, Seq(1, 2), "1234")),
      department = "문화와예술",
      area = "문화와예술",
      credit = 2,
      curEnroll = 0,
      maxEnroll = 70,
      note = ""
    )
  )

  val table = <.table(
    <.thead(
      <.tr(
        <.th("이름"),
        <.th("시간/강의실"),
        <.th("개설학과"),
        <.th("영역"),
        <.th("학점"),
        <.th("신청/제한"),
        <.th("비고")
      )
    ),
    <.tbody(courses.toTagMod(_.toDiv))
  )

  def apply() = table
}

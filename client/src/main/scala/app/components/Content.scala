package app.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Content {
   case class Course(name: String, price: Double, category: String, stocked: Boolean)

  case class State(filterText: String, inStockOnly: Boolean)

  class Backend($: BackendScope[_, State])  {
    def onTextChange(e: ReactEventFromInput) = {
      e.extract(_.target.value)(value =>
        $.modState(_.copy(filterText = value)))
    }

    def onCheckBox(e: ReactEvent) = {
      scalajs.js.Dynamic.global.console.dir(e)
      $.modState(s => s.copy(inStockOnly = !s.inStockOnly))
    }
  }

  val CourseCategoryRow = ScalaComponent.builder[String]("CourseCategoryRow")
    .render_P(category => <.tr(<.th(^.colSpan := 2, category)))
    .build

  val CourseRow = ScalaComponent.builder[Course]("CourseRow")
    .render_P(p =>
      <.tr(
        <.td(<.span(^.color.red.unless(p.stocked), p.name)),
        <.td(p.price))
    )
    .build

  def CourseFilter(s: State)(p: Course): Boolean =
    p.name.contains(s.filterText) &&
      (!s.inStockOnly || p.stocked)

  val CourseTable = ScalaComponent.builder[(List[Course], State)]("CourseTable")
    .render_P { case (courses, state) =>
      val rows = courses.filter(CourseFilter(state))
        .groupBy(_.category).toList
        .flatMap{ case (cat, ps) =>
          CourseCategoryRow.withKey(cat)(cat) :: ps.map(p => CourseRow.withKey(p.name)(p))
        }
        <.table(
          <.thead(
            <.tr(
              <.th("Name"),
              <.th("Price"))),
          <.tbody(
            rows.toVdomArray))
    }
    .build

  val SearchBar = ScalaComponent.builder[(State, Backend)]("SearchBar")
    .render_P { case (s, b) =>
      <.form(
        <.input.text(
          ^.placeholder := "Search Bar ...",
          ^.value       := s.filterText,
          ^.onChange   ==> b.onTextChange),
        <.p(
          <.input.checkbox(
            ^.onClick ==> b.onCheckBox),
          "Only show Courses in stock"))
    }
    .build

  val FilterableCourseTable = ScalaComponent.builder[List[Course]]("FilterableCourseTable")
    .initialState(State("", false))
    .backend(new Backend(_))
    .renderPS(($, p, s) =>
      <.div(
        SearchBar((s,$.backend)),
        CourseTable((p, s))
      )
    ).build

  val Courses = List(
    Course("FootBall", 49.99, "Sporting Goods", true),
    Course("Baseball", 9.99, "Sporting Goods", true),
    Course("basketball", 29.99, "Sporting Goods", false),
    Course("ipod touch", 99.99, "Electronics", true),
    Course("iphone 5", 499.99, "Electronics", true),
    Course("Nexus 7", 199.99, "Electronics", true),
    Course("Haebin", 100000.00, "babo", true),
    Course("Haemin", 10000000000.00, "babo", true)
  )

  val shape = <.div(
    "I'm getting this out!"
  )

  def apply() = shape  //FilterableCourseTable(Courses)
}


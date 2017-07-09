package app

import CssSettings._

object Styles extends StyleSheet.Inline {
  import dsl._
  import scala.language.postfixOps

  val tableMixin = style(display.block)

  val table = style(
    media.only.screen.maxWidth(760 px).minDeviceWidth(768 px).maxDeviceWidth(1024 px)(
      //tableMixin
    )
  )

  val outer = style(
    //common, // Applying our mixin
    margin(12 px, auto),
    textAlign.left,
    cursor.pointer,

    &.hover(
      cursor.zoomIn
    ),

    media.not.handheld.landscape.maxWidth(640 px)(
      width(400 px)
    )
  )

  /** Style requiring an Int when applied. */
  val indent =
    styleF.int(0 to 3)(i => styleS(
                         paddingLeft(i * 2.ex)
                       ))

  /** Style hooking into Bootstrap. */
  val button = style(
    addClassNames("btn", "btn-default")
  )
  val mainFont = fontFace("mainFont")(
    _.src("local(Tahoma)")
      .fontStretch.ultraCondensed
      .fontWeight._200
  )

  val appHeader = style(
    backgroundColor.rgb(34, 34, 34),
    height(150 px),
    padding(20 px),
    color.white
  )

  val appLogo = style(
    height(80 px)
  )

  val appIntro = style(
    fontSize.large
  )

  val app = style(
    textAlign.center,
    fontFamily(mainFont)
  )

  val body = style(
    margin(0 px),
    padding(0 px)
  )
}

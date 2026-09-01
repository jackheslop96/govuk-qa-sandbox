package govuk.pages

trait YesNoPage extends ChoicePage {

  def selectYes(): Unit = select("Yes")

  def selectNo(): Unit = select("No")
}

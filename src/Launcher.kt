import components.ControlPanel
import components.DrawingPanel
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JSplitPane
import javax.swing.border.Border

fun main(args:Array<String>){
    val mainFrame:JFrame = JFrame("Kotlin test");
    mainFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE;
    mainFrame.extendedState = JFrame.MAXIMIZED_BOTH
    mainFrame.layout = BorderLayout()

    val splitPane:JSplitPane = JSplitPane(JSplitPane.HORIZONTAL_SPLIT)
    splitPane.resizeWeight = 0.7
    splitPane.leftComponent = DrawingPanel()
    splitPane.rightComponent = ControlPanel()

    mainFrame.add(splitPane, BorderLayout.CENTER)
    mainFrame.isVisible = true;
}
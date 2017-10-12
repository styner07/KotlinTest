package components

import org.openstreetmap.gui.jmapviewer.JMapViewer
import java.awt.*
import java.awt.event.*

class DrawingPanel : JMapViewer() {

    private val prevPosition:Point = Point(0, 0);
    private val centerPosition:Point = Point();

    init {
        setDisplayPosition(0, 0, MAX_ZOOM)
        tileGridVisible = true

        addMouseMotionListener(object: MouseMotionAdapter(){

            override fun mouseDragged(e: MouseEvent?) {
                super.mouseDragged(e)
                if (e != null){
                    if (prevPosition.x != 0 && prevPosition.y != 0){
                        centerPosition.x += (e.x - prevPosition.x)
                        centerPosition.y += (e.y - prevPosition.y)
                    }

                    prevPosition.x = e.x
                    prevPosition.y = e.y

                    repaint()
                }
            }
        })

        addMouseListener(object: MouseAdapter(){
            override fun mousePressed(e: MouseEvent?) {
                super.mousePressed(e)
                if (e != null){
                    prevPosition.x = e.x
                    prevPosition.y = e.y
                }
            }
        })

        addComponentListener(object: ComponentAdapter(){
            override fun componentMoved(e: ComponentEvent?) {
                super.componentMoved(e)
                invalidate()
            }

            override fun componentResized(e: ComponentEvent?) {
                super.componentResized(e)
                invalidate()
            }
        })
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        drawCircle(g as Graphics2D);

    }
    private val MAX_LENGTH = 800
    private val RADIUS = MAX_LENGTH / 2

    fun drawCircle(g: Graphics2D){
        g.stroke = BasicStroke(1.5f)
        g.color = Color.BLUE

        var map:HashMap<RenderingHints.Key, Any> = HashMap<RenderingHints.Key, Any>()
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.addRenderingHints(map)

        for (i in 0 .. MAX_LENGTH step MAX_LENGTH / 10){
            g.drawOval(centerPosition.x - (i / 2), centerPosition.y - (i / 2), i, i)
        }

        g.drawLine(centerPosition.x, centerPosition.y, centerPosition.x, centerPosition.y + RADIUS);
        g.drawLine(centerPosition.x, centerPosition.y, centerPosition.x, centerPosition.y - RADIUS);
        g.drawLine(centerPosition.x, centerPosition.y, centerPosition.x + RADIUS, centerPosition.y);
        g.drawLine(centerPosition.x, centerPosition.y, centerPosition.x - RADIUS, centerPosition.y);

        g.color = Color(0, 0, 255, 16)
        g.fillOval(centerPosition.x - (MAX_LENGTH / 2), centerPosition.y - (MAX_LENGTH / 2), MAX_LENGTH, MAX_LENGTH);
    }
}
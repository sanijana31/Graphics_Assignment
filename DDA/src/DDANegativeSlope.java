import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.table.*;

public class DDANegativeSlope extends JFrame implements ActionListener {
    // Input Cooradinates
        JTextField x0=new JTextField(5);
        JTextField y0=new JTextField(5);
        JTextField x1=new JTextField(5);
        JTextField y1=new JTextField(5);
    // Plotting area
        JPanel drawPanel=new JPanel();
    // Ploting Table area
        JPanel tablePanel=new JPanel();
    // area for inputing Coardinate
        JPanel topPanel=new JPanel();
    // Table Model
        DefaultTableModel model=new DefaultTableModel();;

    // Constructor
        public DDANegativeSlope() {
            // Exit out of application
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(1366,510);
            // Title of the frame
                setTitle("DDA Algo Implement");
            Initialization();
        }

    public void Initialization(){
        // Button for Drawing and clear Canvas
            JButton draw = new JButton("DRAW");
            JButton clear = new JButton("CLEAR");

        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.add(new JLabel("x0"));
        topPanel.add(x0);
        topPanel.add(new JLabel("y0"));
        topPanel.add(y0);
        topPanel.add(new JLabel("x1"));
        topPanel.add(x1);
        topPanel.add(new JLabel("y1"));
        topPanel.add(y1);
        topPanel.add(draw);
        topPanel.add(clear);

        // Add topPanel to the Frame
            add(topPanel, NORTH);

        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        drawPanel.setBackground(Color.WHITE);
        // Call the action for the buttons
            draw.addActionListener(this);
            clear.addActionListener(this);
        // table for Storing the (x,y) point at every itaretion
            JTable table=new JTable();
            table.setModel(model);
        // Allign the table data at center
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
            renderer.setHorizontalAlignment( SwingConstants.CENTER );
        // Set the Columns Name
            model.addColumn("Point(x,y)");
            model.addColumn("Plot(x,y)");
        // Add Scrollber to the Table and Table to the tablePanel
            tablePanel.add(new JScrollPane(table));
            tablePanel.setPreferredSize(new Dimension(454, 510));

        add(tablePanel, WEST);
        drawPanel.setPreferredSize(new Dimension(300, 510));
        add(drawPanel);
    }
    public void DDAAlgorithm(){
        int stapes;
        double X0=Double.parseDouble(x0.getText());
        double Y0=Double.parseDouble(y0.getText());
        double X1=Double.parseDouble(x1.getText());
        double Y1=Double.parseDouble(y1.getText());
        double dx=X1-X0;
        double dy=Y1-Y0;
        stapes = (int) ((Math.abs(dx) >= Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy));
        if(Math.abs(dx)>=Math.abs(dy)||Math.abs(dx)==0){
            double xincrement=dx/stapes;
            double yincrement=dy/stapes;
            double xx=X0;
            double yy=Y0;
            // Draw the Axes
                drawPanel.getGraphics().drawLine(500,0, 500, 500);
                drawPanel.getGraphics().drawLine(0,218, 894, 218);
            // Add the initial point to the table
                model.addRow(new Object[]{"("+xx+", "+yy+")","("+(int)Math.round(xx)+", "+(int)Math.round(yy)+")"});
            for(int i=0;i<stapes;i++){
                double xTemp=xx;
                double yTemp=yy;
                xx+=xincrement;
                yy+=yincrement;
                // Draw the line with the point of every Itaration
                    drawPanel.getGraphics().drawLine(((int)Math.round(xTemp))+500,218-((int)Math.round(yTemp)),((int)Math.round(xx))+500,218-((int)Math.round(yy)));
                // Add the points of every itaraton to the table
                    model.addRow(new Object[]{"("+String.format("%.1f",xx)+", "+String.format("%.1f",yy)+")","("+(int)Math.round(xx)+", "+(int)Math.round(yy)+")"});
            }
        }
        else {
            drawPanel.getGraphics().drawString("Slope is positive please another combination",350,218);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // if Draw button is pressed
            if(e.getActionCommand().equals("DRAW")){
                DDAAlgorithm();
            }
        // if Clear button is pressed
            else if(e.getActionCommand().equals("CLEAR")){
                // clear the table
                    if (model.getRowCount() > 0) {
                        for (int i = model.getRowCount() - 1; i > -1; i--) {
                            model.removeRow(i);
                        }
                    }
                //clear the draw panel
                    repaint();
            }
    }
    public static void main(String[]args){
        DDANegativeSlope obj=new DDANegativeSlope();
        obj.setVisible(true);
    }
}
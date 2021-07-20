
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.table.*;
public class MidPointCircleDrawingWithArbitaryPoint extends JFrame implements ActionListener {
    JTextField x=new JTextField(5);
    JTextField y=new JTextField(5);
    JTextField radius=new JTextField(5);
    JPanel drawPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    DefaultTableModel model;
    public MidPointCircleDrawingWithArbitaryPoint() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366,510);
        setTitle("Bresenham's Line Drawing Algorithm Implement");
        Initialization();
    }

    public void Initialization(){
        JPanel topPanel=new JPanel();
        topPanel.add(new JLabel("X"));
        topPanel.add(x);
        topPanel.add(new JLabel("Y"));
        topPanel.add(y);
        topPanel.add(new JLabel("radius"));
        topPanel.add(radius);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        drawPanel.setBackground(Color.WHITE);
        JButton draw = new JButton("DRAW");
        JButton clear = new JButton("CLEAR");
        topPanel.add(draw);
        topPanel.add(clear,EAST);
        clear.addActionListener(this);
        draw.addActionListener(this);
        add(topPanel, NORTH);
        model=new DefaultTableModel();
        JTable table=new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        table.setModel(model);
        model.addColumn("1st Q");
        model.addColumn("2nd Q");
        model.addColumn("3rd Q");
        model.addColumn("4th Q");
        tablePanel.add(new JScrollPane(table));
        tablePanel.setPreferredSize(new Dimension(454, 510));
        add(tablePanel, WEST);
        drawPanel.setPreferredSize(new Dimension(300, 510));
        add(drawPanel);
    }
    public void MidPointCircleAlgo(){
        int r=Integer.parseInt(radius.getText());
        int xpre,ypre,x,y,xc,yc;
        x=0;
        y=r;
        xc=Integer.parseInt(this.x.getText());
        yc=Integer.parseInt(this.y.getText());
        double p=1-r;

        drawPanel.getGraphics().drawLine(500,0, 500, 500);
        drawPanel.getGraphics().drawLine(0,218, 894, 218);
        Vector<String> quadrant1 = new Vector<>();
        Vector<String> quadrant2 = new Vector<>();
        Vector<String> quadrant3 = new Vector<>();
        Vector<String> quadrant4 = new Vector<>();
        model.addRow(new Object[]{"("+(x+xc)+", "+(y+yc)+")", "("+(-x+xc)+", "+(y+yc)+")", "("+(-x+xc)+", "+(-y+yc)+")", "("+(x+xc)+", "+(-y+yc)+")"});
        quadrant1.add("("+(y+xc)+", "+(x+yc)+")");
        quadrant2.add("("+(-y+xc)+", "+(x+yc)+")");
        quadrant3.add("("+(-y+xc)+", "+(-x+yc)+")");
        quadrant4.add("("+(y+xc)+", "+(-x+yc)+")");
        while(x<y){
            xpre=x;
            ypre=y;
            if(p<0){
                p+=(2*x+3);
            }
            else{
                p+=(2*x-2*y+5);
                y--;
            }
            x++;

            drawPanel.getGraphics().drawLine((xpre+xc)+500,218-(ypre+yc),(x+xc)+500,218-(y+yc));
            drawPanel.getGraphics().drawLine((ypre+xc)+500,218-(xpre+yc),(y+xc)+500,218-(x+yc));
            quadrant1.add("("+(y+xc)+", "+(x+yc)+")");

            drawPanel.getGraphics().drawLine((-xpre+xc)+500,218-(ypre+yc),(-x+xc)+500,218-(y+yc));
            drawPanel.getGraphics().drawLine((-ypre+xc)+500,218-(xpre+yc),(-y+xc)+500,218-(x+yc));
            quadrant2.add("("+(-y+xc)+", "+(x+yc)+")");

            drawPanel.getGraphics().drawLine((-xpre+xc)+500,218-(-ypre+yc),(-x+xc)+500,218-(-y+yc));
            drawPanel.getGraphics().drawLine((-ypre+xc)+500,218-(-xpre+yc),(-y+xc)+500,218-(-x+yc));
            quadrant3.add("("+(-y+xc)+", "+(-x+yc)+")");

            drawPanel.getGraphics().drawLine((xpre+xc)+500,218-(-ypre+yc),(x+xc)+500,218-(-y+yc));
            drawPanel.getGraphics().drawLine((ypre+xc)+500,218-(-xpre+yc),(y+xc)+500,218-(-x+yc));
            quadrant4.add("("+(y+xc)+", "+(-x+yc)+")");
            model.addRow(new Object[]{"("+(x+xc)+", "+(y+yc)+")", "("+(-x+xc)+", "+(y+yc)+")", "("+(-x+xc)+", "+(-y+yc)+")", "("+(x+xc)+", "+(-y+yc)+")"});
        }
        for(int i=quadrant1.size()-1;i>=0;i--){
            model.addRow(new Object[]{quadrant1.get(i), quadrant2.get(i), quadrant3.get(i), quadrant4.get(i)});
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            MidPointCircleAlgo();
        }
        else if(e.getActionCommand().equals("CLEAR")){
            if (model.getRowCount() > 0) {
                for (int i = model.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }
            repaint();
        }
    }
    public static void main(String[]args){
        MidPointCircleDrawingWithArbitaryPoint obj=new MidPointCircleDrawingWithArbitaryPoint();
        obj.setVisible(true);
    }
}

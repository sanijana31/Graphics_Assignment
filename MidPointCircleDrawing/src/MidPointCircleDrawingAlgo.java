
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.table.*;
public class MidPointCircleDrawingAlgo extends JFrame implements ActionListener {
    JTextField x0=new JTextField(5);
    JPanel drawPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    DefaultTableModel model;
    public MidPointCircleDrawingAlgo() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366,510);
        setTitle("Bresenham's Line Drawing Algorithm Implement");
        Initialization();
    }

    public void Initialization(){
        JPanel topPanel=new JPanel();
        topPanel.add(new JLabel("radius"));
        topPanel.add(x0);
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
        int r=Integer.parseInt(x0.getText());
        int xpre,ypre,x,y;
        x=0;
        y=r;
        double p=1-r;

        drawPanel.getGraphics().drawLine(500,0, 500, 500);
        drawPanel.getGraphics().drawLine(0,218, 894, 218);
        Vector<String> quadrant1 = new Vector<>();
        Vector<String> quadrant2 = new Vector<>();
        Vector<String> quadrant3 = new Vector<>();
        Vector<String> quadrant4 = new Vector<>();
        model.addRow(new Object[]{"("+x+", "+y+")", "("+(-x)+", "+y+")", "("+(-x)+", "+(-y)+")", "("+x+", "+(-y)+")"});
        quadrant1.add("("+y+", "+x+")");
        quadrant2.add("("+-y+", "+x+")");
        quadrant3.add("("+-y+", "+-x+")");
        quadrant4.add("("+y+", "+-x+")");
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

            drawPanel.getGraphics().drawLine(xpre+500,218-ypre,x+500,218-y);
            drawPanel.getGraphics().drawLine(ypre+500,218-xpre,y+500,218-x);
            quadrant1.add("("+y+", "+x+")");

            drawPanel.getGraphics().drawLine(-xpre+500,218-ypre,-x+500,218-y);
            drawPanel.getGraphics().drawLine(-ypre+500,218-xpre,-y+500,218-x);
            quadrant2.add("("+-y+", "+x+")");

            drawPanel.getGraphics().drawLine(-xpre+500,218-(-ypre),-x+500,218-(-y));
            drawPanel.getGraphics().drawLine(-ypre+500,218-(-xpre),-y+500,218-(-x));
            quadrant3.add("("+-y+", "+-x+")");

            drawPanel.getGraphics().drawLine(xpre+500,218-(-ypre),x+500,218-(-y));
            drawPanel.getGraphics().drawLine(ypre+500,218-(-xpre),y+500,218-(-x));
            quadrant4.add("("+y+", "+-x+")");
            model.addRow(new Object[]{"("+x+", "+y+")", "("+(-x)+", "+y+")", "("+(-x)+", "+(-y)+")", "("+x+", "+(-y)+")"});
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
        MidPointCircleDrawingAlgo obj=new MidPointCircleDrawingAlgo();
        obj.setVisible(true);
    }
}



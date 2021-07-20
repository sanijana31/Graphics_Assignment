import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.*;
import javax.swing.table.*;
public class BLDA extends JFrame implements ActionListener {
    JTextField x0=new JTextField(5);
    JTextField y0=new JTextField(5);
    JTextField x1=new JTextField(5);
    JTextField y1=new JTextField(5);
    JPanel drawPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    DefaultTableModel model;
    public BLDA() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366,510);
        setTitle("Bresenham's Line Drawing Algorithm Implement");
        Initialization();
    }

    public void Initialization(){
        JPanel topPanel=new JPanel();
        topPanel.add(new JLabel("x0"));
        topPanel.add(x0);
        topPanel.add(new JLabel("y0"));
        topPanel.add(y0);
        topPanel.add(new JLabel("x1"));
        topPanel.add(x1);
        topPanel.add(new JLabel("y1"));
        topPanel.add(y1);
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
        model.addColumn("pk");
        model.addColumn("pk+1");
        model.addColumn("Point(x,y)");
        tablePanel.add(new JScrollPane(table));
        tablePanel.setPreferredSize(new Dimension(454, 510));
        add(tablePanel, WEST);
        drawPanel.setPreferredSize(new Dimension(300, 510));
        add(drawPanel);
    }
    public void BLDAAlgorithm(){
        int X0=Integer.parseInt(x0.getText());
        int Y0=Integer.parseInt(y0.getText());
        int X1=Integer.parseInt(x1.getText());
        int Y1=Integer.parseInt(y1.getText());
        int dx=X1-X0;
        int dy=Y1-Y0;
        int f=1;
        int pk,xx,yy;
        if(dy<dx){
            pk=2*dy-dx;
            xx=X0;
            yy=Y0;
            model.addRow(new Object[]{pk,"","("+xx+", "+yy+")"});
        }
        else{
            pk=2*dx-dy;
            yy=X0;
            xx=Y0;
            dx=dx+dy;
            dy=dx-dy;
            dx=dx-dy;
            f=0;
            model.addRow(new Object[]{pk,"","("+yy+", "+xx+")"});
            X1=Y1;
        }
        drawPanel.getGraphics().drawLine(500,0, 500, 500);
        drawPanel.getGraphics().drawLine(0,218, 894, 218);
        int xTemp,yTemp,pkTemp;
        do{
            xTemp=xx;
            yTemp=yy;
            pkTemp=pk;
            xx+=1;
            if(pk>=0){
                yy++;
                pk+=((2*dy)-(2*dx*(yy-yTemp)));
            }
            else{
                pk+=(2*dy);
            }
            if(f==1){
                drawPanel.getGraphics().drawLine(xTemp+500,218-yTemp,xx+500,218-yy);
                model.addRow(new Object[]{pkTemp, pk, "("+xx+", "+yy+")"});
            }
            else{
                drawPanel.getGraphics().drawLine(yTemp+500,218-xTemp,yy+500,218-xx);
                model.addRow(new Object[]{pkTemp, pk, "("+yy+", "+xx+")"});
            }
        }while(xx<X1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            BLDAAlgorithm();
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
        BLDA obj=new BLDA();
        obj.setVisible(true);
    }
}


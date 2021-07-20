import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.table.*;
public class DirectLineEquation extends JFrame implements ActionListener {
    JTextField x0=new JTextField(5);
    JTextField y0=new JTextField(5);
    JTextField x1=new JTextField(5);
    JTextField y1=new JTextField(5);
    JPanel drawPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    JPanel topPanel=new JPanel();
    DefaultTableModel model;
    public DirectLineEquation() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366,510);
        setTitle("Bresenham's Line Drawing Algorithm Implement");
        Initialization();
    }

    public void Initialization(){
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
        model.addColumn("Point(x,y)");
        tablePanel.add(new JScrollPane(table));
        tablePanel.setPreferredSize(new Dimension(454, 510));
        add(tablePanel, WEST);
        drawPanel.setPreferredSize(new Dimension(300, 510));
        add(drawPanel);
    }
    public void DirectLineEquationAlgorithm(){
        int stapes;
        double X0=Double.parseDouble(x0.getText());
        double Y0=Double.parseDouble(y0.getText());
        double X1=Double.parseDouble(x1.getText());
        double Y1=Double.parseDouble(y1.getText());
        double dx=X1-X0;
        double dy=Y1-Y0;
        double m=dy/dx;
        double d=dx/Math.abs(dx);
        double c=Y0-m*X0;
        double xx=X0;
        double yy=Y0;
        drawPanel.getGraphics().drawLine(500,0, 500, 500);
        drawPanel.getGraphics().drawLine(0,218, 894, 218);
        model.addRow(new Object[]{"("+xx+", "+yy+")"});
        while(xx!=X1&&Math.abs(xx)<Math.abs(X1)){
            double xTemp=xx;
            double yTemp=yy;
            xx+=d;
            yy=m*xx+c;
            drawPanel.getGraphics().drawLine((int)Math.round(xTemp)+500,218-(int)Math.round(yTemp),(int)Math.round(xx)+500, 218 -(int)(Math.round(yy)));
            model.addRow(new Object[]{"("+String.format("%.1f",xx)+", "+String.format("%.1f",yy)+")"});
        }
        drawPanel.getGraphics().drawLine((int)Math.round(xx)+500,218-(int)Math.round(yy),(int)Math.round(X1)+500, 218 -(int)(Math.round(Y1)));
        if(xx!=X1){
            model.addRow(new Object[]{"("+String.format("%.1f",X1)+", "+String.format("%.1f",Y1)+")"});
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            DirectLineEquationAlgorithm();
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
        DirectLineEquation obj=new DirectLineEquation();
        obj.setVisible(true);
    }
}


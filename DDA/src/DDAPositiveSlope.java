import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.table.*;
public class DDAPositiveSlope extends JFrame implements ActionListener {
    JTextField x0=new JTextField(5);
    JTextField y0=new JTextField(5);
    JTextField x1=new JTextField(5);
    JTextField y1=new JTextField(5);
    JPanel drawPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    DefaultTableModel model;
    public DDAPositiveSlope() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366,510);
        setTitle("DDA Algo Implement");
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
        topPanel.add(clear);
        draw.addActionListener(this);
        clear.addActionListener(this);
        add(topPanel, NORTH);
        model=new DefaultTableModel();
        JTable table=new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        table.setModel(model);
        model.addColumn("Point(x,y)");
        model.addColumn("Plot(x,y)");
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
        stapes = (int) ((Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy));
        if(Math.abs(dx)<Math.abs(dy)||Math.abs(dy)==0){
            double xincrement=dx/stapes;
            double yincrement=dy/stapes;
            double xx=X0;
            double yy=Y0;
            drawPanel.getGraphics().drawLine(500,0, 500, 500);
            drawPanel.getGraphics().drawLine(0,218, 894, 218);
            model.addRow(new Object[]{"("+xx+", "+yy+")","("+(int)Math.round(xx)+", "+(int)Math.round(yy)+")"});
            for(int i=0;i<stapes;i++){
                double xTemp=xx;
                double yTemp=yy;
                xx+=xincrement;
                yy+=yincrement;
                drawPanel.getGraphics().drawLine(((int)(Math.round(xTemp)))+500,218-((int)(Math.round(yTemp))),((int)(Math.round(xx)))+500,218-((int)(Math.round(yy))));
                model.addRow(new Object[]{"("+String.format("%.1f",xx)+", "+String.format("%.1f",yy)+")","("+(int)Math.round(xx)+", "+(int)Math.round(yy)+")"});
            }
        }
        else {
            drawPanel.getGraphics().drawString("Slope is negative please another combination",350,218);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            DDAAlgorithm();
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
        DDAPositiveSlope obj=new DDAPositiveSlope();
        obj.setVisible(true);
    }
}

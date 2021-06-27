import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MidPointLineAlgo {
    public static void main(String []args){
        double x0,y0,x1,y1,d,m;
        x0=y1=x1=y0=d=m=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter initial point");
        try {
            x0=Float.parseFloat(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            y0=Float.parseFloat(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("enter ending point");
        try {
            x1=Float.parseFloat(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            y1=Float.parseFloat(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        double dx=x1-x0;
        double dy=y1-y0;
        m=dy/dx;
        if(m<1){
            d=dy-dx/2;
        }
        else{
            d=dx-dy/2;
        }
        System.out.println("dx= "+dx+"\tdy= "+dy+"\td= "+d+"\tm= "+m);
        double x,y;
        x=x0;y=y0;
        int c=1;
        System.out.println("step= initial\t"+"x= "+x+"\ty= "+y+"\tdinitial= \t"+"dnew= \t"+"plot(x(k+1),y(k+1))= ("+x+", "+y+")");
        while(x<x1&&y<y1){
            double d0=d;
            double xk=x,yk=y;
            if(d<=0){
                if(m<1){
                    d+=dy;
                    x++;
                }
                else{
                    d+=dx;
                    y++;
                }
            }
            else{
                if(m<1){
                    d+=(dy-dx);
                    x++;
                    y++;
                }
                else{
                    d+=(dx-dy);
                    x++;
                    y++;
                }
            }
            System.out.println("step= "+(c++)+"\tx= "+xk+"\ty= "+yk+"\tdinitial= "+d0+"\tdnew= "+d+"\tplot(x(k+1),y(k+1))= ("+x+", "+y+")");
        }
    }
}

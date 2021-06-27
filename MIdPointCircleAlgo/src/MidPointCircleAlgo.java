import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MidPointCircleAlgo {
    public static void main(String [] args){
        System.out.println("enter the redius");
        double r=0,x=0,y=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try {
            r=Float.parseFloat(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        x=0;y=r;
        double p=1-r;
        while(x<y){
            System.out.print("xk= "+x+"\tyk= "+y+"\tpk= "+p+"\tplot(x(k+1),y(k+1))= (");
            if(p<0){
                p+=(2*x+3);
            }
            else{
                p+=(2*x-2*y+5);
                y--;
            }
            x++;
            System.out.println(x+", "+y+")");
        }
    }
}

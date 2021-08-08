//Lab Exercise 16
//Liang Barsky algorithm


#include<stdio.h>
#include<graphics.h>
#include<math.h>
#include<conio.h>
#include<dos.h>
 
int main()
{
	int i,gd=DETECT,gm;
	int x1,y1,x2,y2,xmin,xmax,ymin,ymax,dx,dy;
	float t1=0,t2=1,p[4],q[4],temp;
	printf("enter xmin,xmax,ymin,ymax of the window :");
	scanf("%d%d%d%d",&xmin,&xmax,&ymin,&ymax);
	printf("enter first end point of the line:");
	scanf("%d%d",&x1,&y1);
	printf("enter second end point of the line:");
	scanf("%d%d",&x2,&y2);
	
	initgraph(&gd,&gm,(char*)"");
	//rectangle(xmin,ymin,xmax,ymax);
	line(xmin,ymin,xmin,ymax);
	line(xmin,ymin,xmax,ymin);
	line(xmax,ymax,xmin,ymax);
	line(xmax,ymax,xmax,ymin);
	line(x1,y1,x2,y2);
	dx=x2-x1;
	dy=y2-y1;
	p[0]=(float)-dx; 
	p[1]=(float)dx;
	p[2]=(float)-dy;
	p[3]=(float)dy;
	q[0]=(float)(x1-xmin);
	q[1]=(float)(xmax-x1);
	q[2]=(float)(y1-ymin);
	q[3]=(float)(ymax-y1);
	//printf("%f,%f,%f,%f",q[0],q[1],q[2],q[3]);
	if(p[0]==0){
		printf("\nParallel to the Y Axis");
		if(q[0]*q[1]<0){
			printf("\nOut Of the window");
		}
		else{
			if(y1<ymin){
				y1=ymin;
			}
			if(y2>ymax){
				y2=ymax;
			}
		}
	}
	else if(p[2]==0){
		printf("\nParallel to the X Axis");
		if(q[2]*q[3]<0){
			printf("\nOut Of the window");
		}
		else{
			if(x1<xmin){
				x1=xmin;
			}
			if(x2>xmax){
				x2=xmax;
			}
		}
		printf("%d,%d,%d,%d",x1,y1,x2,y2);	
	}
	else{
		t1=0;
		t2=1;
		for(i=0;i<4;i++){
			temp=q[i]/p[i];
			if(p[i]<0)
			{
				if(t1<=temp)
				t1=temp;
				
			}
			else
			{
				if(t2>temp)
				t2=temp;
			}
		//	printf("%d->%d\n",i,temp);
			//printf("%d->t1=%d,t2=%d\n",i,t1,t2);
		}
		
		if(t1<t2){
			y2 =y1+ (int)(t2*p[3]);
			x2 =x1+ (int)(t2*p[1]);
			x1 += (int)(t1*p[1]);
			y1 += (int)(t1*p[3]);
		
		}
		else{
			printf("\nline is outside");
		}
	}
	setcolor(RED);
	line(x1,y1,x2,y2);
	printf("\n Clipped line :%d\t%d\t%d\t%d",x1,y1,x2,y2);
	//delay(5000);
	getch();
	closegraph();
}

#include<graphics.h>
#include<conio.h>
#include<stdio.h>
#include<math.h>
float slope;
int x,y,xx,yy,i, xc,yc;
int rcode_begin[4]={0,0,0,0},rcode_end[4]={0,0,0,0},region_code[4];
int X_wmax,Y_wmax,X_wmin,Y_wmin,flag=0;
int *res=NULL;
void calc()
{
	if(y>Y_wmax)  {
		rcode_begin[0]=1;     // Top
		flag=1 ;
	}
	if(y<Y_wmin) {
		rcode_begin[1]=1;           // Bottom
		flag=1;
	}
	if(x>X_wmax)  {
		rcode_begin[2]=1;           // Right
		flag=1;
	}
	if(x<X_wmin)   {
		rcode_begin[3]=1;           //Left
		flag=1;
	}
	
	//end point of Line
	if(yy>Y_wmax){
		rcode_end[0]=1;           // Top
		flag=1;
	}
	if(yy<Y_wmin) {
		rcode_end[1]=1;           // Bottom
		flag=1;
	}
	if(xx>X_wmax){
		rcode_end[2]=1;           // Right
		flag=1;
	}
	if(xx<X_wmin){
		rcode_end[3]=1;           //Left
		flag=1;
	}
	if(flag==0)
	{
		printf("\n No need of clipping as it is already in window");
	}
	flag=1;
	for(i=0;i<4;i++){
		region_code[i]= rcode_begin[i] && rcode_end[i] ;
		if(region_code[i]==1){
			flag=0;
		}
	}
	if(flag==0)
	{
		printf("\n Line is completely outside the window");
	}
	else{
		slope=(float)(yy-y)/(xx-x);
		if(rcode_begin[2]==0 && rcode_begin[3]==1)   //left
		{
			y=y+(float) (X_wmin-x)*slope ;
			x=X_wmin;
		}
		if(rcode_begin[2]==1 && rcode_begin[3]==0)       // right
		{
			y=y+(float) (X_wmax-x)*slope ;
			x=X_wmax;
		}
		if(rcode_begin[0]==1 && rcode_begin[1]==0)      // top
		{
			x=x+(float) (Y_wmax-y)/slope ;
			y=Y_wmax;
		}
		if(rcode_begin[0]==0 && rcode_begin[1]==1)     // bottom
		{
			x=x+(float) (Y_wmin-y)/slope ;
			y=Y_wmin;
		}
		// end points
		if(rcode_end[2]==0 && rcode_end[3]==1)   //left
		{
			yy=yy+(float) (X_wmin-xx)*slope ;
			xx=X_wmin;
		}
		if(rcode_end[2]==1 && rcode_end[3]==0)       // right
		{
			yy=yy+(float) (X_wmax-xx)*slope ;
			xx=X_wmax;
		}
		if(rcode_end[0]==1 && rcode_end[1]==0)      // top
		{
			xx=xx+(float) (Y_wmax-yy)/slope ;
			yy=Y_wmax;
		}
		if(rcode_end[0]==0 && rcode_end[1]==1)     // bottom
		{
			xx=xx+(float) (Y_wmin-yy)/slope ;
			yy=Y_wmin;
		}
	}
	res[0]=x;
	res[1]=y;
	res[2]=xx;
	res[3]=yy;
}
int main()
{
	res=(int*)calloc(4,sizeof(int));
	int gr=DETECT,gm;
	initgraph(&gr,&gm,(char*)"");
	printf("\nCohen Sutherland Line Clipping algorithm");
	cleardevice();
	line(20,20,20,80);
	line(20,20,80,20);
	line(80,80,20,80);
	line(80,20,80,80);
	X_wmax=80;
	Y_wmax=80;
	X_wmin=20;
	Y_wmin=20;
	x=40;
	y=70;
	xx=60;
	yy=30;
	line(x,y,xx,yy);
	calc();
	delay(1000);
	clearviewport();
	line(20,20,20,80);
	line(20,20,80,20);
	line(80,80,20,80);
	line(80,20,80,80);
	setcolor(GREEN);
	line(res[0],res[1],res[2],res[3]);
	x=110;
	y=80;
	xx=40;
	yy=10;
	calc();
	line(res[0],res[1],res[2],res[3]);
	getch();
	closegraph();
}

/* Question 14 */
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
	int i;
	if(y>Y_wmax)  {
		rcode_begin[1]=1;     // Top
		flag=1 ;
	}
	if(y<Y_wmin) {
		rcode_begin[0]=1;           // Bottom
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
		rcode_end[1]=1;           // Top
		flag=1;
	}
	if(yy<Y_wmin) {
		rcode_end[0]=1;           // Bottom
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
	printf("begin %d-%d-%d-%d\n",rcode_begin[0],rcode_begin[1],rcode_begin[2],rcode_begin[3]);
	printf("end %d-%d-%d-%d\n",rcode_end[0],rcode_end[1],rcode_end[2],rcode_end[3]);
}
int main()
{
	int gr=DETECT,gm;
	initgraph(&gr,&gm,(char*)"");
	printf("\nCohen Sutherland Line Clipping algorithm");
	cleardevice();
	line(30,30,30,70);
	line(30,30,90,30);
	line(90,30,90,70);
	line(90,70,30,70);
	X_wmax=90;
	Y_wmax=70;
	X_wmin=30;
	Y_wmin=30;
	x=50;
	y=80;
	xx=70;
	yy=40;
	//setcolor();
	line(x,y,xx,yy);
	res=(int*)calloc(4,sizeof(int));
	calc();
	//delay(1000);
	line(30,30,30,70);
	line(30,30,90,30);
	line(90,30,90,70);
	line(90,70,30,70);
	setcolor(RED);
	line(x,y,xx,yy);
	delay(1000);
	cleardevice();
	setcolor(WHITE);
	line(30,30,30,70);
	line(30,30,90,30);
	line(90,30,90,70);
	line(90,70,30,70);
	//setcolor(RED);
	//line(55,70,70,40);
	printf("\n%d-%d-%d-%d\n",res[0],res[1],res[2],res[3]);
	x=120;
	y=90;
	xx=50;
	yy=20;
	res=(int*)calloc(4,sizeof(int));
	//setcolor(BLUE);
	line(x,y,xx,yy);
	calc();
	setcolor(RED);
	line(x,y,xx,yy);
	//setcolor(RED);
	//line(100,70,60,30);
	printf("%d-%d-%d-%d\n",res[0],res[1],res[2],res[3]);
	getch();
	closegraph();
}

#include<iostream>
#include<stdlib.h>
#include<math.h>
using namespace std;
typedef struct point{
	double x,y;
	struct point *next;
}Point;
void LeftRightClipping(Point **headPolygon,double xw,int d){
	Point *head=NULL;
	Point *ptr=NULL;
	Point *newptr=NULL;
	Point *newPoint=NULL;
	Point *last=NULL,*lastpre=NULL;
	double x0,y0,x1,y1,m,x=(*headPolygon)->x;
	int f=0;
	if(d*x>=(d*xw)){
		f=1;
		newPoint=new Point();
		newPoint->x=(*headPolygon)->x;
		newPoint->y=(*headPolygon)->y;
		newPoint->next=head;
		head=newPoint;
		newptr=newPoint;
	}
	ptr=(*headPolygon);
	do{
		x0=ptr->x;
		y0=ptr->y;
		x1=ptr->next->x;
		y1=ptr->next->y;
		m=(y1-y0)/(x1-x0);
		if(d*x0<d*xw&&d*x1<d*xw){
			ptr=ptr->next;
			continue;
		}
		else if(d*x0<d*xw&&d*x1>=d*xw){
			y0=y0+((xw-x0)*m);
			x0=xw;
			newPoint=new Point();
			newPoint->x=x0;
			newPoint->y=y0;
			newPoint->next=head;
			if(head==NULL){
				head=newPoint;
			}
			else{
				newptr->next=newPoint;
			}
			newptr=newPoint;
		}
		else if(d*x0>=d*xw&&d*x1<d*xw){
			y1=y1+((xw-x1)*m);
			x1=xw;
		}
		newPoint=new Point();
		newPoint->x=x1;
		newPoint->y=y1;
		newPoint->next=head;
		if(head==NULL){
			head=newPoint;
		}
		else{
			newptr->next=newPoint;
		}
		last=newptr;
		newptr=newPoint;
		ptr=ptr->next;
	}while(ptr!=(*headPolygon));
	if(f==1){
		last->next=head;
	}
	(*headPolygon)=head;
}
void TopBottomClipping(Point **headPolygon,double yw,int d){
	Point *head=NULL;
	Point *ptr=NULL;
	Point *newptr=NULL;
	Point *newPoint=NULL;
	Point *last=NULL,*lastpre=NULL;
	double x0,y0,x1,y1,m,y=(*headPolygon)->y;
	int f=0;
	if(d*y>=d*yw){
		f=1;
		newPoint=new Point();
		newPoint->x=(*headPolygon)->x;
		newPoint->y=(*headPolygon)->y;
		newPoint->next=head;
		head=newPoint;
		newptr=newPoint;
	}
	ptr=(*headPolygon);
	do{
		x0=ptr->x;
		y0=ptr->y;
		x1=ptr->next->x;
		y1=ptr->next->y;
		m=(y1-y0)/(x1-x0);
		if(d*y0<d*yw&&d*y1<d*yw){
			ptr=ptr->next;
			continue;
		}
		else if(d*y0<d*yw&&d*y1>=d*yw){
			x0=x0+((yw-y0)/m);
			y0=yw;
			newPoint=new Point();
			newPoint->x=x0;
			newPoint->y=y0;
			newPoint->next=head;
			if(head==NULL){
				head=newPoint;
			}
			else{
				newptr->next=newPoint;
			}
			newptr=newPoint;
		}
		else if(d*y0>=d*yw&&d*y1<d*yw){
			x1=x1+((yw-y1)/m);
			y1=yw;
		}
		newPoint=new Point();
		newPoint->x=x1;
		newPoint->y=y1;
		newPoint->next=head;
		if(head==NULL){
			head=newPoint;
		}
		else{
			newptr->next=newPoint;
		}
		last=newptr;
		newptr=newPoint;
		ptr=ptr->next;
	}while(ptr!=(*headPolygon));
	if(f==1){
		last->next=head;
	}
	(*headPolygon)=head;
}
int main(){
	Point *headPolygon=NULL;
	Point *ptr=NULL;
	Point *newPoint=NULL;
	int choise;
	double x,y;
	double xmin,ymin,xmax,ymax;
	cout<<"enter the xmin, ymin, xmax, ymax\n";
	cin>>xmin>>ymin>>xmax>>ymax;
	cout<<"enter the polygon\n";
	do{
		cout<<"enter the point(x,y): ";
		newPoint=new point();
		cin>>x>>y;
		newPoint->x=x;
		newPoint->y=y;
		newPoint->next=headPolygon;
		if(headPolygon==NULL){
			headPolygon=newPoint;
		}
		else{
			ptr->next=newPoint;
		}
		ptr=newPoint;
		cout<<"enter the '1' if polygon is ot complete otherwise enter '0': ";
		cin>>choise;
	}while(choise!=0);
	cout<<"\nbefore clipping\n";
	ptr=headPolygon;
	if(headPolygon!=NULL&&headPolygon->next!=NULL){
		do{
			cout<<"("<<ptr->x<<","<<ptr->y<<")->";
			ptr=ptr->next;
		}while(ptr!=headPolygon);
	}
	else{
		cout<<"not a polygon\n";
	}
	//left clip
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		LeftRightClipping(&headPolygon,xmin,1);
	}
	cout<<"\nafter left clipping\n";
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		ptr=headPolygon;
		do{
			cout<<"("<<ptr->x<<","<<ptr->y<<")->";
			ptr=ptr->next;
		}while(ptr!=headPolygon);
	}
	else{
		cout<<"it's fully outside\n";
	}
	//top clip
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		TopBottomClipping(&headPolygon,ymin,1);
	}
	cout<<"\nafter top clipping\n";
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		ptr=headPolygon;
		do{
			cout<<"("<<ptr->x<<","<<ptr->y<<")->";
			ptr=ptr->next;
		}while(ptr!=headPolygon);
	}
	else{
		cout<<"it's fully outside\n";
	}
	
	//right clip
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		LeftRightClipping(&headPolygon,xmax,-1);
	}
	cout<<"\nafter right clipping\n";
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		ptr=headPolygon;
		do{
			cout<<"("<<ptr->x<<","<<ptr->y<<")->";
			ptr=ptr->next;
		}while(ptr!=headPolygon);
	}
	else{
		cout<<"it's fully outside\n";
	}
	
	//bottom clip
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		TopBottomClipping(&headPolygon,ymax,-1);
	}
	cout<<"\nafter bottom clipping\n";
	if(headPolygon!=NULL&&headPolygon->next!=headPolygon){
		ptr=headPolygon;
		do{
			cout<<"("<<ptr->x<<","<<ptr->y<<")->";
			ptr=ptr->next;
		}while(ptr!=headPolygon);
	}
	else{
		cout<<"it's fully outside\n";
	}
	return 0;
}

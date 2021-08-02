#include<iostream>
#include<stdlib.h>
using namespace std;
int main(){
	int ax,ay,bx,by,cx,cy,tx,ty;
	int factor;
	cout<<"enter the 1st point(a): ";
	cin>>ax>>ay;
	cout<<"enter the 2nd point(b): ";
	cin>>bx>>by;
	cout<<"enter the 3rd point(c): ";
	cin>>cx>>cy;
	int choise;
	cout<<"enter which point do you want to fixed 1 for a, 2 for b, 3 for c : ";
	cin>>choise;
	switch(choise){
		case 1:
			tx=-ax;
			ty=-ay;
			break;
		case 2:
			tx=-bx;
			ty=-by;
			break;
		case 3:
			tx=-cx;
			ty=-cy;
			break;
		default:
			tx=-cx;
			ty=-cy;
			break;
	}
	//transform
	ax+=tx; ay+=ty;
	bx+=tx; by+=ty;
	cx+=tx; cy+=ty;
	cout<<"enter the scaling factor: ";
	cin>>factor;
	cout<<"a=("<<ax<<", "<<ay<<"), "<<"\tb=("<<bx<<", "<<by<<"), "<<"\tc=("<<cx<<", "<<cy<<")\n";
	//Scaling
	ax*=factor; ay*=factor;
	bx*=factor; by*=factor;
	cx*=factor; cy*=factor;
	cout<<"a=("<<ax<<", "<<ay<<"), "<<"\tb=("<<bx<<", "<<by<<"), "<<"\tc=("<<cx<<", "<<cy<<")\n";
	//transform back
	ax-=tx; ay-=ty;
	bx-=tx; by-=ty;
	cx-=tx; cy-=ty;
	cout<<"a=("<<ax<<", "<<ay<<"), "<<"\tb=("<<bx<<", "<<by<<"), "<<"\tc=("<<cx<<", "<<cy<<")\n";
	return 0;
}

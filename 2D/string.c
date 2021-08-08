#include<iostream>
#include<stdlib.h>
using namespace std;
void main(){
	char *a;
	int *b;
	int i,n;
	cin>>n;
	a=(char*)malloc(sizeof(char)*n);
	b=(int*)malloc(sizeof(int)*26);
	for(i=0;i<n;i++){
		cin>>a[i];
		if(a[i]>=65||a[i]<91){
			b[((int)a[i]-65)]=1;
		}
		else if(a[i]>=97||a[i]<123){
			b[((int)a[i]-97)]=1;
		}
	}
	for(i=0;i<26;i++){
		if(b[i]!=1){
			cout<<(char)(i+65)<<" ";
		}
	}
}

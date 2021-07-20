#include<stdio.h>
#include<graphics.h>
#include<conio.h>

// flood fill algorithm
void flood(int x, int y, int new_col, int old_col)
{	
	if (getpixel(x, y) == old_col)
	{
		putpixel(x, y, new_col);
		flood(x + 1, y, new_col, old_col);
		flood(x - 1, y, new_col, old_col);
		flood(x, y + 1, new_col, old_col);
		flood(x, y - 1, new_col, old_col);
		flood(x + 1, y+1, new_col, old_col);
		flood(x - 1, y+1, new_col, old_col);
		flood(x-1, y + 1, new_col, old_col);
		flood(x-1, y - 1, new_col, old_col);
	}
}

int main()
{
	int gd=DETECT, gm;
	initgraph(&gd,&gm,(char*)"");
	int top, left, bottom, right;
	printf("enter left corner :");
	scanf("%d", &left);  
	printf("enter top corner :");
	scanf("%d", &top); 
	printf("enter right corner :");
	scanf("%d", &right);
	printf("enter bottom corner :");
	scanf("%d", &bottom);  
	rectangle(left, top, right, bottom);
	int x = (left+((right-left)/2));
	int y = (top+((bottom-top)/2));
	int newcolor = 10;
	int oldcolor = 0;
	flood(x, y, newcolor, oldcolor);
	delay(10000);
	getch();
	
	return 0;
}

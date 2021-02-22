#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>
#include <string.h>

void f(int* polje, int n, int m) {

	if (n > 1) {
		f(&polje[0], n - 1, m);
	}
	
	polje[n - 1] = pow(m, n - 1);
	
	return;
}


int main(void) {
	int *polje;
	int i;
	int n = 5;
	int m = 10;
	polje = (int *)malloc(n * sizeof(int));
	
	f(&polje[0], n, m);

	for (i = 0; i < n; i++) {
		printf("%d ", polje[i]);
	}
	scanf("%d", &n);
	return 0;
}

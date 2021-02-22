#include <stdio.h>
#include <stdlib.h>

void spojiPolja(int *A, int *B, int *Rez, int n1, int n2) {
	int i = 0, j = 0;
	while (n1 != 0 && n2 != 0) {
		if (A[i] > B[j]) {
			Rez[i + j] = A[i];
			i++;
			n1--;
		}
		else {
			Rez[i + j] = B[j];
			j++;
			n2--;
		}
	}
	while (n1 != 0) {
		Rez[i + j] = A[i];
		i++;
		n1--;
	}
	while (n2 != 0) {
		Rez[i + j] = B[j];
		j++;
		n2--;
	}
}


int main(void) {
	int i;
	int *p;
	int A[] = { 8,7,6 };
	int B[] = { 2,1,0 };
	/*if (p = (int *) malloc (12*sizeof(int)) == NULL) exit (1);*/
	p = (int *)malloc(12 * sizeof(int));
	spojiPolja(&A[0], &B[0], p, 3, 3);
	for (i = 0; i < 6; i++) {
		printf("%d ", p[i]);
	}
	scanf("%d", &i);
	return 0;
}



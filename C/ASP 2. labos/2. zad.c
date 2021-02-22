#include <stdio.h>
#include <stdlib.h>



void selection2Sort (int *A, int n, char smjer) {
 int i, max, min, j, pom;
 k = n-1;
 for (i = 0; i < n; n--, i++) {
  min = A[i];
  max = A[n-1];
  for (j = i; j < n; j++) {
   if (A[j] < min) min = A[j];
   if (A[j] > max) max = A[j];
  
   if (A[j] <= min) {
	pom = A[i];
	A[i] = A[j];
	A[j] = pom;
   }

   if (A[j] >= max) {
	pom = A[n-1];
	A[n-1] = A[j];
	A[j] = pom;
   }
  }
 }
}

int main (void) {
 int i;
 int polje[]={0,2,5,3,2,6};
 selection2Sort (&polje[0], 6, 1);
 for (i = 0; i < 6; i++) printf ("%d ", polje[i]);
 return 0;
}


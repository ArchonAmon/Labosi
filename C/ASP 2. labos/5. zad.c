#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void spojiNizove(char *A, char *B, char *Rez) {
	int i = 0, j = 0;
	int n1 = sizeof (A) + 1;
	int n2 = sizeof (B) + 1;
	while (n1 != 0 && n2 != 0) {
		if (A[i] >= B[j]) {
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
	char *p;
	char P[15];
	char A[] = { "ecaDB" };
	char B[] = { "dbECA" };
	int i;
	spojiNizove(&A[0], &B[0], &P[0]);
	p = (char *)malloc(10 * sizeof(char));
	for (i = 0; i < 10; i++) printf("%c ", P[i]);
	scanf("%d", &i);
	return 0;
}
s


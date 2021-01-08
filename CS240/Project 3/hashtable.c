/*
 * Name, hw3.c, CS 24000, Fall 2019
 * Last updated Sept, 2019
 */

// PROVIDED TO STUDENTS

/*DO NOT CHANGE----------BEGIN*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"hashtable.h"

int main() {
    char ch;
    char name[100];
    struct hash_table_element* hash_table = 0;
    int table_size, new_table_size;
    while (1) {
        printf("\nWelcome to Hashtable\n");
        printf("--------------------\n");
        printf("A-Initialize hash table\n");
        printf("B-Compute hash value\n");
        printf("C-Insert name\n");
        printf("D-Load names from file\n");
        printf("E-Delete name\n");
        printf("F-Search name\n");
        printf("G-Number of empty elements\n");
        printf("H-Average list length\n");
        printf("I-Maximum list length\n");
        printf("J-Resize hash table (Extra Credit)\n");
        printf("K-Display hash table\n");
        printf("L-Delete hash table\n");
        printf("M-Exit\n");
        printf("--------------------\n");
        printf("Select an option: ");
        scanf("%c",&ch);
        getchar();
        if (ch != 'M' && ch != 'A' && hash_table == NULL) {
            printf("Initialize hash table first\n");
            exit(0);
        }
        switch (ch) {
            case 'A':
                printf("Input number of hash table elements: ");
                scanf("%d", &table_size);
                getchar();
                hash_table =  init_hash_table(table_size);
                break;
            case 'B':
                printf("Enter the name whose hash value to compute: ");
                scanf("%s", name);
                getchar();        
                printf("hash value: %d\n", get_hash(name, table_size));
                break;
            case 'C':
                printf("Enter the name to insert: "); 
                scanf("%s", name);
                getchar();
                insert_name(hash_table, table_size, name);
                printf("Inserted name: %s\n", name);
                break;
            case 'D':
                printf("Enter the name of the input file: ");
                scanf("%s", name);
                getchar();
                load_from_file(hash_table, table_size, name);
                break;
            case 'E':
                printf("Enter the name to delete: ");
                scanf("%s", name);
                getchar();
                if (delete_name(hash_table, table_size, name)) {
                    printf("Deleted name: %s\n", name);
                }
                else {
                    printf("Name not found: %s\n", name);
                }
                break;
            case 'F':
                printf("Enter the name to search: ");
                scanf("%s", name);
                getchar();
                if (search_in_hash_table(hash_table, table_size, name)) {
                    printf("Found name: %s\n", name);
                }
                else {
                    printf("Name not found: %s\n", name);
                }
                break;
            case 'G':
                printf("Number of empty hash table elements: %d\n",
                    compute_stats_empty(hash_table, table_size));
                break;
            case 'H':
                printf("Average list length: %.2f\n",
                    compute_stats_average(hash_table, table_size));
                break;
            case 'I':
                printf("Length of longest list: %d\n",
                    compute_stats_max(hash_table, table_size));
                break;
            case 'J':
                printf("Enter the new number of hash table elements: ");
                scanf("%d", &new_table_size);
                getchar();
                hash_table = resize_hash_table(hash_table, table_size, new_table_size);
                table_size = new_table_size;
                break;
            case 'K':
                display(hash_table, table_size);
                break;
            case 'L':
                free_hashtable(hash_table, table_size);
                break;
            case 'M':
                exit(0);
            default:
                printf("You have entered wrong option!\n");
                break;
        }
    }
}


void load_from_file(struct hash_table_element* hash_table, int size, char *filename) {
    FILE* fp= fopen(filename,"r");
    assert(fp);
    int num=0;
    fscanf(fp, "%d\n", &num);
    for(int i=0;i<num;i++){
        char name[100];
        fscanf(fp, "%s",name);
        insert_name(hash_table, size, name);
   }
    fclose(fp);
}

void display(struct hash_table_element* hash_table, int table_size) {
    struct sll_node *ptr;
    printf("\nData in Hash Table:\n");
    for (int i = 0; i < table_size; i++) {
        printf("Index %d:", i);
        if (hash_table[i].head == NULL) {
            printf("NULL\n");            
            continue;
        }
        ptr = hash_table[i].head;
        while (ptr != NULL) {
            printf(" %s ->", ptr->name);
            ptr = ptr->next;
        }
        printf(" NULL\n");
    }
}

/*DO NOT CHANGE----END*/
// END PROVIDED TO STUDENTS


// PART 1
struct hash_table_element* init_hash_table(int table_size) {
	struct hash_table_element* array = (struct hash_table_element*) malloc(sizeof(struct hash_table_element) * table_size);
	for (int i = 0; i < table_size; i++) {
		array[i].head = NULL;
	}
	return array; // feel free to modify this line    
    
}

// PART 2
int get_hash(char* name, int table_size) {
	//struct hash_table_element* table = (struct hash_table_elements*) malloc(sizeof(struct hash_table_element) * table_size);
	int i = 0;
	int sum = 0;
	while (name[i] != '\0') {
		sum += name[i];
		i++;
	}
	return ((sum * sum) % table_size);
  
    // write your code here
    // feel free to modify this line
    
}

// PART 3
void insert_name(struct hash_table_element* hash_table, 
                 int size, char *name) {
	int position = get_hash(name, size);
	struct sll_node* newNode = (struct sll_node*) malloc(sizeof(struct sll_node));
	newNode->name = (char*) malloc(sizeof(char) * (strlen(name) + 1));
	strcpy(newNode->name, name);
	newNode->next = hash_table[position].head;
	hash_table[position].head = newNode;

    // write your code here

    
}


// PART 4
int delete_name(struct hash_table_element* hash_table, 
                int table_size, char* name) {
	int position = get_hash(name, table_size);
	int answer = 0;
	struct sll_node* curr = hash_table[position].head;
	struct sll_node* prev = NULL;
	while (curr) {
		if (strcmp(curr->name, name) == 0) {
			if (prev == NULL) {
				hash_table[position].head = curr->next;
				free(curr);
				answer = 1;
			} else {
				prev->next = curr->next;
				free(curr);
				answer = 1;
			}
		}
		prev = curr;
		curr = curr->next;
	}
	/*
	while (current != NULL) {
		if (counter == 1) {
			previous = hash_table[position].head;
		}
		if (counter >= 2) {
			previous = previous->next;
		}
		if (current->name == name && name == hash_table[position].head->name) {
			hash_table[position].head = current->next;
			free(current);
			answer = 1;
			break;
		} else if (current->name == name) {
			previous->next = current->next;
			free(current);
			answer = 1;
			break;
		}
		current = current->next;
		counter++;
	}
	*/

    
    // write your code here
    return answer; // feel free to modify this line
    
}

// PART 5
int search_in_hash_table(struct hash_table_element* hash_table, int table_size, char* name) {
	int position = get_hash(name, table_size);
 	struct sll_node* curr = hash_table[position].head;
	int answer = 0;
	while (curr) {
		if (strcmp(curr->name, name) == 0) {
			answer = 1;
		}
		curr = curr->next;
	}	
    // write your code here
    return answer; // feel free to modify this line

    
}

// PART 6
int compute_stats_empty(struct hash_table_element* hash_table, int table_size){
	int counter = 0;
	for (int i = 0; i < table_size; i++) {
		if (hash_table[i].head == NULL) {
			counter++;
		}
	}
    
    // write your code here
    return counter; // feel free to modify this line
        
}

// PART 7
float compute_stats_average(struct hash_table_element* hash_table, int table_size){
	float elementCounter = 0.0;
	float listCounter = 0.0;
	for (int i = 0; i < table_size; i++) {
		struct sll_node* curr = hash_table[i].head;
		if (curr != NULL) {
			while (curr != NULL) {
				listCounter++;
				curr = curr->next;
			}
			elementCounter++;
		}
	}
    
    // write your code here    
    return listCounter / elementCounter; // feel free to modify this line
    
}

// PART 8
int compute_stats_max(struct hash_table_element* hash_table, int table_size){
	int array[table_size];
	for (int i = 0; i < table_size; i++) {
		int listCounter = 0;
		struct sll_node* curr = hash_table[i].head;
		if (curr != NULL) {
			while (curr != NULL) {
				listCounter++;
				curr = curr->next;
			}
		}
		array[i] = listCounter;
	}
	int max = 0;
	for (int i = 0; i < table_size; i++) {
		if (array[i] > max) {
			max = array[i];
		}

	}
    
    // write your code here
    return max; // feel free to modify this line
        
}


// PART 9
void free_hashtable(struct hash_table_element* hash_table, int table_size){
	for (int i = 0; i < table_size; i++) {
		struct sll_node* curr = hash_table[i].head;
		struct sll_node* prev = hash_table[i].head;
		while (curr != NULL) {
			curr = curr->next;
			free(prev->name);
			free(prev);
			prev = curr;
		}
	}
	free(hash_table);
    // write your code here
    
}


// EXTRA CREDIT
struct hash_table_element* resize_hash_table(struct hash_table_element* old_table, int old_size, int new_size){
	struct hash_table_element* newTable = init_hash_table(new_size);
	struct sll_node* curr;
	for (int i = 0; i < new_size; i++) {
		curr = old_table[i].head;
		while (curr != NULL) {
			insert_name(newTable, old_size, curr->name);
			curr = curr->next;
		}
	}
	struct sll_node* newCurr;
	struct sll_node* newPrev;
	for (int i = new_size; i < old_size; i++) {
		newCurr = old_table[i].head;
		newPrev = old_table[i].head;
		while (newCurr != NULL) {
			newCurr = newCurr->next;
			free(newPrev->name);
			free(newPrev);
			newPrev = curr;
		}
	}

    
    // write your code here    
    return newTable; // feel free to modify this line
    
}


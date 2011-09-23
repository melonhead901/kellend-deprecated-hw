// Kellen Donohue
// 303 Assignment 3
// 02-04-2009

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#define MAX_FILE_LENGTH	 500

// Gets the next line from the text file, returns the pointer to that line
char* getLine(char* loc, FILE* fileStream);
// Returns whether the given string contains the specified search pattern
int checkStringMatch(char* toSearch, char* pattern);
// Returns a lowercase copy of the string
char* switchToLower(char* string);
// Prints the given line to stdout, with lineNum if nonzero
void printLine(int lineNum, char* fileName, char* text);
// Processes a file, printing any lines in that file that match the given search pattern
void processfile(char* filename, char* pattern );
// Checks for possible options and returns the number of enabled options
int checkoptions(char** argv);

// Global variables hold option state
int optionn = 0;
int optioni = 0;

int main(int argc, char**argv)
{
	// Make sure the minimum number of arguments is present
	if(argc < 3)
	{
		fputs("Must provide a search pattern and at least 1 file.\n", stderr);
		return 1;
	}

	// Number of enabled options, used to detemine where search pattern is in argv
	int numoptions = checkoptions(argv);

	// Holds the search pattern
	char* pattern = argv[numoptions+ 1];
	
	// If "-i" is used, change the pattern to lowercase
	if(optioni)
	{
		pattern = switchToLower(pattern);
	}

	int i;
	for(i=numoptions + 2; i < argc; i++)
	{
		char* filename = argv[i];
		processfile(filename, pattern);		
	}
	// the switchToLower function resulted in pattern being malloc'd
	if(optioni)
	{
		free(pattern);
	}
	return 0;
}

int checkoptions(char** argv)
{
	int numOptions = 0;
	if(checkStringMatch(argv[1], "-n") || checkStringMatch(argv[2], "-n"))
	{
		optionn = 1;
		numOptions++;
	}
	if(checkStringMatch(argv[1], "-i") || checkStringMatch(argv[2], "-i"))
	{
		optioni = 1;
		numOptions++;
	}	
	return numOptions;
}

void processfile(char* filename, char* pattern)
{
	FILE* f = fopen(filename, "r");

	// If the file does not exist print an error message and return, otherwise process it
	if(!f)
	{
		fprintf(stderr, "File %s unopenable.\n", filename);
		return;
	}

	// Will hold each line scanned in from the file
	char* line = (char*)malloc(MAX_FILE_LENGTH*sizeof(char)); 
	int lineNum = 1;			 
	while(!feof(f))
	{			
		line = getLine(line, f);

		// Only proceed if the line is non-null
		if(line)
		{
			// If the "-i" option is used compare the string after stripping case
			if(optioni)
			{
				char* lowerLine = switchToLower(line);
				if(checkStringMatch(lowerLine, pattern))
				{
					printLine(lineNum*optionn, filename, line);
				}
				free(lowerLine);					
			}
			else
			{
				if(checkStringMatch(line, pattern))
				{
					printLine(lineNum*optionn, filename, line);
				}
			}		
			lineNum++;
		}
	}

	// Close file and free line
	fclose(f);	
	free(line);
}

char* getLine(char* loc, FILE* fileStream)
{
	return fgets(loc, MAX_FILE_LENGTH, fileStream);
}

int checkStringMatch(char* toSearch, char* pattern)
{	char* exists = strstr(toSearch, pattern);
	if(exists)
	{
		return 1;
	}
	return 0;
}

char* switchToLower(char* str)
{	
	char* newString = (char*)malloc(sizeof(char)*strlen(str));

	//Iterate through charaters in str, switch each letter to lowercase
	int i;
	for(i = 0; str[i]; i++)
	{
		newString[i]=tolower(str[i]);		
	}

	return newString;
}

void printLine(int lineNum, char* fileName, char* text)
{
	if(lineNum)
	{		
		printf("%d %s %s", lineNum, fileName, text);
	}
	else
	{
		printf("%s %s", fileName, text);
	}
}

// Kellen Donohue
// 303 Assignment 3
// 02-04-2009

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#define MAX_FILE_LENGTH	 500

// Gets the next line from the text file, returns the pointer to that line
char* getLine(char* loc, FILE* fileStream, int* reachedEndOfLine);
// Returns whether the given string contains the specified search pattern
char* checkStringMatch(char* toSearch, char* pattern);
// Returns a lowercase copy of the string
char* switchToLower(char* string);
// Prints the given line to stdout, with lineNum if nonzero
void printLine(int lineNum, char* fileName, char* text);
// Processes a file, printing any lines in that file that match the given search pattern, with a temporary string used to hold the most recently read line
void processfile(char* filename, char* pattern, char* tempLine);
// Checks for possible options and returns the number of enabled options
int checkoptions(char** argv);
// Checks to see if the given pattern exists surrounded by whitespace
int checkWS(char* toSearch, char*pattern);

// Global variables hold option state
int optionn = 0;
int optioni = 0;
int optionw = 0;

int main(int argc, char**argv)
{
	// Make sure the minimum number of arguments is present
	if(argc < 2)
	{
		fputs("Must provide a search pattern.\n", stderr);
		return 1;
	}

	// Number of enabled options, used to detemine where search pattern is in argv
	int numoptions; 
	// If there are only 2 total arguments, the program location and the search pattern there can be no options, so no need to check
	if (argc==2)
	{
		numoptions = 0;
	}
	else
	{
		numoptions = checkoptions(argv);
	}

	// Holds the search pattern
	char* pattern = argv[numoptions+ 1];

	// If "-i" is used, change the pattern to lowercase
	if(optioni)
	{
		pattern = switchToLower(pattern);
	}

	// Will hold each line scanned in from the file
	char* line = (char*)malloc(MAX_FILE_LENGTH*sizeof(char)); 

	// Support for reading from standard in
	// Activates only if the only provided option, besides arguments is standard in
	if(argc-numoptions==2)
	{
		processfile("", pattern, line);
	}
	else
	{
		int i;
		for(i=numoptions + 2; i < argc; i++)
		{
			char* filename = argv[i];
			processfile(filename, pattern, line);		
		}
	}

	free(line);	

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

	if(checkStringMatch(argv[1], "-n") || checkStringMatch(argv[2], "-n") || checkStringMatch(argv[3], "-n"))
	{
		optionn = 1;
		numOptions++;
	}

	if(checkStringMatch(argv[1], "-i") || checkStringMatch(argv[2], "-i") || checkStringMatch(argv[3], "-i"))
	{
		optioni = 1;
		numOptions++;
	}

	if(checkStringMatch(argv[1], "-w") || checkStringMatch(argv[2], "-w") || checkStringMatch(argv[3], "-w"))
	{
		optionw = 1;
		numOptions++;
	}

	return numOptions;
}

void processfile(char* filename, char* pattern, char* line)
{
	FILE* f;

	// If just opening from stdin
	if(strlen(filename)==0)
	{
		f=stdin;
	}
	else
	{	
		f = fopen(filename, "r");

		// If the file does not exist print an error message and return, otherwise process it
		if(!f)
		{
			fprintf(stderr, "File %s unopenable.\n", filename);
			return;
		}
	}

	int lineNum = 1;			 
	while(!feof(f))
	{
		int reachedendofline = 0;
		line = getLine(line, f, reachendofline*);

		// Only proceed if the line is non-null
		if(line)
		{
			// If the "-i" option is used compare the string after stripping case
			if(optioni)
			{
				char* lowerline = switchToLower(line);
				if(optionw)
				{
					if(checkWS(lowerline, pattern))
					{
						printLine(lineNum*optionn, filename, line);
					}
				}
				else if(checkStringMatch(lowerline, pattern))
				{
					printLine(lineNum*optionn, filename, line);
				}
				free(lowerline);					
			}
			else
			{
				if(optionw)
				{
					if(checkWS(line, pattern))
					{
						printLine(lineNum*optionn, filename, line);
					}
				}
				else if(checkStringMatch(line, pattern))
				{
					printLine(lineNum*optionn, filename, line);
				}
			}
			lineNum += reachedendofline;
		}
	}

	// Close file and free line
	fclose(f);		
}

int checkWS(char* toSearch, char* pattern)
{
	char* location = checkStringMatch(toSearch, pattern);
	int difference = location - toSearch;
	if(location)
	{
		int before = toSearch[difference-1];
		if(before)
		{
			if(!isspace(before))
			{
				return 0;
			}
		}
		int after = toSearch[difference+1];
		if(after)
		{
			return isspace(after);
		}
		return 1;
	}
	else
	{
		return 0;
	}
}

char* getLine(char* loc, FILE* fileStream, int* reachedEndOfLine)
{
	char* line =  fgets(loc, MAX_FILE_LENGTH, fileStream);
	
	if(strpbrk(line, "\n"))
	{
		&reachedEndOfLine  = 1;
	}

	return line;
}

char* checkStringMatch(char* toSearch, char* pattern)
{
	if(!toSearch)
		return NULL;	
	char* exists = strstr(toSearch, pattern);
	return exists;
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

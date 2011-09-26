#######################################
# Kellen Donohue
# CSE 378 - Fall 2010
# Homework 2
# Due: Monday Oct. 11th, 5pm
#######################################

### Data section
	.section	.rodata
.haystack:
	.string	"KeKelKel"
.needle:
	.string	"Kel"
	.align 8 
.LC2:
	.string	"Found \"%s\" in string \"%s\" at index %lu \n"
.LC3:
	.string	"\"%s\" not found in string \"%s\" \n"
.text2:
	.string "String of length %d\n"
	.align 8

### Text section
	.text
### Function declarations
.globl strstr
	.type	strstr, @function
.globl main
	.type	main, @function

#######################################
# INT MAIN(INT ARGC, CHAR* ARGV[])
#######################################
main:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	movq	%rsp, %rbp
	.cfi_offset 6, -16
	.cfi_def_cfa_register 6
	pushq	%rbx
	subq	$56, %rsp
	movl	%edi, -52(%rbp)
	movq	%rsi, -64(%rbp)
	movq	$.haystack, -24(%rbp)
	movq	$.needle, -32(%rbp)
	movq	-32(%rbp), %rdx # needle
	movq	-24(%rbp), %rax # haystack
	movq	%rdx, %rsi # needle
	movq	%rax, %rdi # haystack
	.cfi_offset 3, -24
	call	strstr
	movq	%rax, -40(%rbp)
	cmpq	$0, -40(%rbp)
	je	.L4
	movq	-40(%rbp), %rdx
	movq	-24(%rbp), %rax
	movq	%rdx, %rcx
	subq	%rax, %rcx
	movl	$.LC2, %eax
	movq	-24(%rbp), %rdx
	movq	-32(%rbp), %rbx
	movq	%rbx, %rsi
	movq	%rax, %rdi
	movl	$0, %eax
	call	printf
	jmp	.L5
.L4:
	movl	$.LC3, %eax
	movq	-24(%rbp), %rdx
	movq	-32(%rbp), %rcx
	movq	%rcx, %rsi
	movq	%rax, %rdi
	movl	$0, %eax
	call	printf
.L5:
	movl	$0, %eax
	addq	$56, %rsp
	popq	%rbx
	leave
	ret
	.cfi_endproc


#######################################
# CHAR* STRSTR(CHAR* STRING, CHAR* TARGET)
#######################################
strstr:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	movq	%rsp, %rbp
	.cfi_offset 6, -16
	.cfi_def_cfa_register 6
	movq	%rdi, -8(%rbp) # haystack
	movq	%rsi, -16(%rbp) # needle

#######################################
# YOUR CODE GOES BELOW THIS LINE AND
# Determine the length of the needle (similar to version in Oskin's email)
	movq $0, %rcx			# Store length in rcx
	mov (%rsi), %al			# Check if the needle is null
	cmpb $0, %al
	je .start				# If it is return a pointer to the beginning of the string
.length:
	inc %rcx				# Increment the count and start position
	inc %rsi
	mov (%rsi), %al			# See if we're done (at end of the needle)
	cmpb $0, %al
	jne .length				# If we aren't increment again
	mov %rcx, -24(%rbp)		# If we are store the length
.loop:						# Check if the needle haystack is at the start of the haystack
	movq -24(%rbp), %rcx	# Restore the string variables for comparison
	movq -16(%rbp), %rsi
	movq -8(%rbp), %rdi 
	mov (%rdi), %al			# Check if the haystack is null
	cmpb $0, %al	
	je .nothing				# If the haystack is null we didn't find the string, jump out
	repe cmpsb				# Checks if rsi and rdi are the same (up to rcx chars), this
							# determines if the needle is at the current start of haystack
	jne .imp				# We didn't find the needle, so increment 
							# the start of haystack and retry
	mov -8(%rbp), %rax		# We did the needle, so return the current position of the haystack
	jmp .return
.imp:
	add $1, -8(%rbp)		# Increment the start of the haystack and go back to the loop
	jmp .loop			
.nothing:					# We didn't find the string, return 0
	mov $0, %rax
	jmp .return
.start:						# The needle was null, return a pointer to the beginning of the string
	mov -8(%rbp), %rax
	jmp .return
.return:

#         ABOVE THIS LINE
#######################################
	leave
	ret
	.cfi_endproc

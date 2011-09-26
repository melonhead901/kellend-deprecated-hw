; Kellen Donohue
; 10-02-10
; CSE 378 - HW1
; hw1-prog2.s (Non-optimized)
	.file	"hw1-prog2.c"
	.text
.globl my_strange_function
	.type	my_strange_function, @function ; Store my_strange_function as a global symbol, it's a function
my_strange_function:
.LFB5:
	pushq	%rbp				; Push the function args onto the stack
.LCFI0:
	movq	%rsp, %rbp			; Move the stack pointer to the args
.LCFI1:							; main function body
	movq	%rdi, -24(%rbp)		; Store x in the heap
	movq	-24(%rbp), %rax		; Store x in the register
	movq	%rax, -16(%rbp)		; set result = x
	movq	$0, -8(%rbp)		; set i = 0
	movq	$0, -8(%rbp)		; Don't know why this appears twice
	jmp	.L2						; Goto L2 (for loop)
.L3:							; body of for()
	movq	-8(%rbp), %rax		; Stores i in rax
	leaq	-16(%rbp), %rdx		; Stires result in rax rdx
	xorq	%rax, (%rdx)		; XOR i and result, store in i
	leaq	-8(%rbp), %rax		; Restore the value of i
	incq	(%rax)				; increment i
.L2:							; line of for()
	movq	-8(%rbp), %rax		; Get i, store in rax
	cmpq	-24(%rbp), %rax		; Comapre i to x
	jl	.L3						; if i < x jump to L3 (enter body of for)
	movq	-24(%rbp), %rax		; Store the result of the function in rax, so we can return x
	leave						; leave the subroutine
	ret							; return x (rax)
.LFE5:
	.size	my_strange_function, .-my_strange_function
	.section	.rodata			; not real sure what this does
.LC0:
	.string	"result: %llx\n"	; text we will display at the end of the program
	.text
.globl main					; Store main as a global symbol, it's a function
	.type	main, @function 
main:
.LFB6:
	pushq	%rbp				; Push the function args onto the stack
.LCFI2:
	movq	%rsp, %rbp			; Set the stack pointer to the args we just pushed
.LCFI3:
	subq	$32, %rsp			; subtract 32 from the stack pointer
.LCFI4:							; main()
	movl	%edi, -4(%rbp)		; Get argc off the heap
	movq	%rsi, -16(%rbp)		; Get argv from the heap?
	cmpl	$2, -4(%rbp)		; Check if argc==2
	je	.L7						; If it is jump to L7
	movl	$0, -20(%rbp)		; Store 0 as a return value
	jmp	.L9						; Jump to L9
.L7:							; line 17 of program
	movq	-16(%rbp), %rax		; Get argv, put in rax
	addq	$8, %rax			; Set rax 8 bits ahead, since we want argv[1]
	movq	(%rax), %rdi		; Set the value at rax to be the destination of a string op
	movl	$0, %eax			; Move 0 into eax for the function call
	call	atoi				; convert the arg to an integer, this will go into rax
	movslq	%eax,%rdi			; Move the string into a register so my_strange_function can use it
	call	my_strange_function	; call my strange function on the result, store result in rax
	movq	%rax, %rsi			; Move the result of the function call to the source of a string op
	movl	$.LC0, %edi			; Get the static text we want to display
	movl	$0, %eax			; set the result of the function as the arg to be handed to printf
	call	printf				; print the result to the screen
	movl	$1, -20(%rbp)		; Store 1 on the heap, to be returned later
.L9:
	movl	-20(%rbp), %eax		; Pull the return value from the heap
	leave						; Leave the subroutine
	ret							; Return the value from eax
	
; Debugging symbols?
.LFE6:
	.size	main, .-main
	.section	.eh_frame,"a",@progbits
.Lframe1:
	.long	.LECIE1-.LSCIE1
.LSCIE1:
	.long	0x0
	.byte	0x1
	.string	""
	.uleb128 0x1
	.sleb128 -8
	.byte	0x10
	.byte	0xc
	.uleb128 0x7
	.uleb128 0x8
	.byte	0x90
	.uleb128 0x1
	.align 8
.LECIE1:
.LSFDE1:
	.long	.LEFDE1-.LASFDE1
.LASFDE1:
	.long	.LASFDE1-.Lframe1
	.quad	.LFB5
	.quad	.LFE5-.LFB5
	.byte	0x4
	.long	.LCFI0-.LFB5
	.byte	0xe
	.uleb128 0x10
	.byte	0x86
	.uleb128 0x2
	.byte	0x4
	.long	.LCFI1-.LCFI0
	.byte	0xd
	.uleb128 0x6
	.align 8
.LEFDE1:
.LSFDE3:
	.long	.LEFDE3-.LASFDE3
.LASFDE3:
	.long	.LASFDE3-.Lframe1
	.quad	.LFB6
	.quad	.LFE6-.LFB6
	.byte	0x4
	.long	.LCFI2-.LFB6
	.byte	0xe
	.uleb128 0x10
	.byte	0x86
	.uleb128 0x2
	.byte	0x4
	.long	.LCFI3-.LCFI2
	.byte	0xd
	.uleb128 0x6
	.align 8
.LEFDE3:
	.ident	"GCC: (GNU) 4.0.3 (Ubuntu 4.0.3-1ubuntu5)" ; The program that compiled this assembly
	.section	.note.GNU-stack,"",@progbits

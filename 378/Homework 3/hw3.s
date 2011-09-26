#######################################
# Kellen Donohue
# CSE 378 - Fall 2010
# Homework 3
# Due: Monday Oct. 18th, 5pm
#######################################

### Data section
	.section	.rodata
.LC0:
	.string	"%li "

	.data
	.align 32
	.type	arr.2223, @object
	.size	arr.2223, 80
arr.2223:
	.quad	-1    
	.quad	-2    
	.quad	-3    
	.quad	4
	.quad	-55
	.quad	-56
	.quad	4
	.quad	-5
	.quad	4
	.quad	-10

### Text section
	.text
# Function declarations
.globl partition
    .type   partition, @function
.globl quicksort
	.type	quicksort, @function
.globl swap
	.type	swap, @function
.globl main
	.type	main, @function

###################################
# int main(int argc, char* argv[])
###################################
main:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	movq	%rsp, %rbp
	.cfi_offset 6, -16
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movl	%edi, -20(%rbp)
	movq	%rsi, -32(%rbp)
	movq	$9, %rdx
	movq	$0, %rsi
	movq	$arr.2223, %rdi
	call	quicksort
	movl	$0, -4(%rbp)
	jmp	.L6
.L7:
	movl	-4(%rbp), %eax
	cltq
	movq	arr.2223(,%rax,8), %rdx
	movl	$.LC0, %eax
	movq	%rdx, %rsi
	movq	%rax, %rdi
	movl	$0, %eax
	call	printf
	addl	$1, -4(%rbp)
.L6:
	cmpl	$9, -4(%rbp)
	jle	.L7
	movl	$0, %eax
	leave
	ret
	.cfi_endproc

###############################################
# void swap(int64_t A[], int64_t i, int64_t j)
###############################################
swap:
	# Adjust the stack frame and save necessary registers
	pushq	%rbp
	movq 	%rsp, %rbp
	subq	$24, %rsp
	movq	%rdi, 0(%rsp)	# int64_t*
	movq	%rsi, 8(%rsp)	# int64_t i
	movq	%rdx, 16(%rsp)	# int64_t j
	# Get A[i] into %r8
	salq	$3, %rsi
	addq	%rdi, %rsi
	movq 	(%rsi), %r8		# temp = A[i]
	# Get A[j] into A[i]
	salq	$3, %rdx
	addq	%rdi, %rdx
	movq	(%rdx), %r9		# %r9 = A[j]
	movq	%r9, (%rsi)		# A[i] = A[j]
	# Get %r8 (temp) into A[j]
	movq	%r8, (%rdx)		# A[j] = temp
	# Restore save registers, pop stack and return
	movq	16(%rsp), %rdx
	movq	8(%rsp), %rsi
	movq	0(%rsp), %rdi
	leave
	ret

#####################################################
# void quicksort (int64_t A[], int64_t p, int64_t r)
#####################################################
quicksort:
	# Adjust the stack frame and save necessary registers
	pushq	%rbp
	movq 	%rsp, %rbp
	subq	$32, %rsp
	movq	%rdi, 0(%rsp)	# int64_t*
	movq	%rsi, 8(%rsp)	# int64_t p
	movq	%rdx, 16(%rsp)	# int64_t r    
    
    # If p >= r then return:
    cmp    %rdx, %rsi
    jnl    .return
    
    # q = partition (A, p, r)
    call    partition
    mov     %rax, 24(%rsp)  # 24(%rsp) = q
    mov     %rax, %rdx      # Move q to be the 3rd arg for func call
    # quicksort (A, p, q)
    call    quicksort
    add     $1, 24(%rsp)
    mov     24(%rsp), %rsi  # Move q+1 to be the 2nd arg for func call
    mov     16(%rsp), %rdx  # Move r to tbe the 3rd arg for func call
    # quicksort (A, q+1, r)
    call    quicksort
.return:
	# Restore save registers, pop stack and return
	movq	16(%rsp), %rdx
	movq	8(%rsp), %rsi
	movq	0(%rsp), %rdi
    leave
    ret
######################################################
# void partition (int64_t A[], int64_t p, int64_y, q)
######################################################
partition:	
    # Adjust the stack frame and save necessary registers
	pushq	%rbp
	movq 	%rsp, %rbp
	subq	$24, %rsp
	movq	%rdi, 0(%rsp)	# int64_t*
	movq	%rsi, 8(%rsp)	# int64_t p
	movq	%rdx, 16(%rsp)	# int64_t q
    
    # pivot = A[p]
    # %r10 = pivot
    mov     (%rdi,%rsi,8), %r10
    
    sub     $1, %rsi            # i = p-1
    add     $1, %rdx            # j = q+1    
    
# while (true)
# repeat j = j-1 until A[j] <= pivot
.sub_j:
    sub     $1, %rdx            # j = j-1
    cmp     %r10, (%rdi,%rdx,8)
    jle     .add_i              # next step
    jmp     .sub_j              # repeat
# repeat i = i+1 until A[i] >= pivot
.add_i:
    add     $1, %rsi            # i = i+1
    cmp     %r10, (%rdi,%rsi,8)
    jge     .swap               # next step
    jmp     .add_i              # repeat
.swap:
    cmpq     %rdx, %rsi         # if i < j 
    jl      .doswap             # then swap(A, i, j)
    jmp     .part_return        # else return j  
.doswap:
    call    swap                # Args already set up for func call
    jmp     .sub_j              # Continue while loop
# Restore save registers, pop stack and return
.part_return:
    mov     %rdx, %rax
	movq	16(%rsp), %rdx
	movq	8(%rsp), %rsi
	movq	0(%rsp), %rdi
    leave
    ret

package net.runelite.asm.attributes.code.instructions;

import net.runelite.asm.attributes.code.Instruction;
import net.runelite.asm.attributes.code.InstructionType;
import net.runelite.asm.attributes.code.Instructions;
import net.runelite.asm.attributes.code.instruction.types.ArrayLoad;
import net.runelite.asm.execution.Frame;
import net.runelite.asm.execution.InstructionContext;
import net.runelite.asm.execution.Stack;
import net.runelite.asm.execution.StackContext;

public class CALoad extends Instruction implements ArrayLoad
{
	public CALoad(Instructions instructions, InstructionType type, int pc)
	{
		super(instructions, type, pc);
	}

	@Override
	public InstructionContext execute(Frame frame)
	{
		InstructionContext ins = new InstructionContext(this, frame);
		Stack stack = frame.getStack();
		
		StackContext index = stack.pop();
		StackContext array = stack.pop();
		
		ins.pop(index, array);
		
		StackContext ctx = new StackContext(ins, int.class, array.getValue().arrayGet(index.getValue()).cast(int.class)); // zero extended to int
		stack.push(ctx);
		
		ins.push(ctx);
		
		return ins;
	}
}

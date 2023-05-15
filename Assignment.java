import java.util.HashMap;
import java.util.Map;

public class SimpleAssemblyInterpreter {
    private Map<String, Integer> registers;

    public SimpleAssemblyInterpreter() {
        registers = new HashMap<>();
    }

    public void executeProgram(String[] program) {
        for (String instruction : program) {
            if (instruction.startsWith("MV")) {
                move(instruction);
            } else if (instruction.startsWith("ADD")) {
                add(instruction);
            } else if (instruction.startsWith("SHOW")) {
                show(instruction);
            }
        }
    }

    private void move(String instruction) {
        String[] parts = instruction.split(" ");
        String register = parts[1];
        int value = Integer.parseInt(parts[2].substring(1));
        registers.put(register, value);
    }

    private void add(String instruction) {
        String[] parts = instruction.split(" ");
        String register1 = parts[1];
        String operand = parts[2];
        int value;

        if (operand.startsWith("#")) {
            value = Integer.parseInt(operand.substring(1));
        } else {
            String register2 = operand;
            value = registers.get(register2);
        }

        int currentValue = registers.get(register1);
        registers.put(register1, currentValue + value);
    }

    private void show(String instruction) {
        String register = instruction.split(" ")[1];
        int value = registers.get(register);
        System.out.println("Register " + register + ": " + value);
    }

    public static void main(String[] args) {
        SimpleAssemblyInterpreter interpreter = new SimpleAssemblyInterpreter();

        // Example program
        String[] program = {
                "MV REG1, #2000",
                "MV REG2, #4000",
                "ADD REG1, REG2",
                "ADD REG1, 600",
                "SHOW REG1"
        };

        interpreter.executeProgram(program);
    }
}

import java.awt.*;
import javax.swing.*;

public class ToDoListApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;

    public ToDoListApp() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input field
        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(taskField, BorderLayout.NORTH);

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton markDoneButton = new JButton("Mark as Done");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(markDoneButton);
       
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            }
        });

        markDoneButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = taskListModel.getElementAt(selectedIndex);
                if (!task.startsWith("[Done] ")) {
                    taskListModel.set(selectedIndex, "[Done] " + task);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ToDoListApp();
    }
}

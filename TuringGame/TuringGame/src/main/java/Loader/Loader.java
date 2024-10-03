package Loader;

import Model.TuringException;

import java.io.*;
/**
 * The Loader class is responsible for loading and processing data from a CSV file.
 * It provides static methods to read specific lines, extract code elements, and list all problems.
 */
public class Loader {



    private static final String PROBLEMS_FILE_PATH = "/known_problems.csv";

    /**
     * Retrieves a specific line from the problems file by its line number.
     *
     * @param lineNumber The line number to retrieve.
     * @return The line at the specified line number.
     * @throws TuringException If there is an error reading the file.
     */
    public static String getLineByLineNumber(int lineNumber) {
        try (InputStream inputStream = Loader.class.getResourceAsStream(PROBLEMS_FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    return line;
                }
                currentLine++;
            }
        } catch (IOException e) {
            throw new TuringException("Error reading problem file: " + e.getMessage());
        }
        return null;
    }



    /**
     * Extracts the code element from a given line in the problems file.
     *
     * @param line The line from which to extract the code element.
     * @return The code element as an integer.
     * @throws TuringException If the line is empty.
     */
    public static int getCodeElementFromLine(String line) {
        if (line == null) {
            throw new TuringException("Line is empty.");
        }
        String[] values = line.split(",");
        return Integer.parseInt(values[3].trim());
    }


    /**
     * Extracts validator numbers from a given line in the problems file.
     *
     * @param line The line from which to extract validator numbers.
     * @return An array of validator numbers.
     * @throws TuringException If the line is empty.
     */
    public static int[] getValidatorsFromLine(String line) {
        if (line == null) {
            throw new TuringException("Line is empty.");
        }
        String[] values = line.split(",");
        int[] validatorNumbers = new int[values.length - 4];

        for (int i = 4; i < values.length; i++) {
            validatorNumbers[i - 4] = Integer.parseInt(values[i].trim());
        }
        return validatorNumbers;
    }


    /**
     * Lists all problems by reading and processing each line in the problems file.
     *
     * @return A string representation of all problems, including their number, difficulty, and luck.
     * @throws TuringException If there is an error reading the file.
     */
    public static String listAllProblems() {
        StringBuilder problemsList = new StringBuilder();

        try (InputStream inputStream = Loader.class.getResourceAsStream(PROBLEMS_FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 4) {
                    int number = Integer.parseInt(values[0].trim());
                    int difficulty = Integer.parseInt(values[1].trim());
                    int luck = Integer.parseInt(values[2].trim());

                    problemsList.append(number)
                            .append("- Difficulty: ").append(difficulty)
                            .append(", Luck: ").append(luck).append("\n");
                }
            }
        } catch (IOException e) {
            throw new TuringException("Error reading problem file: " + e.getMessage());
        }

        return problemsList.toString();
    }
}

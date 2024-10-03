#ifndef TETRISEXCEPTION_H
#define TETRISEXCEPTION_H
#include <exception>
#include <string>


class TetrisException : public std::exception {
private:
    std::string message;  // Custom error message

public:
    // Constructor that takes a custom error message
    inline explicit TetrisException(const std::string& msg) : message(msg) {}

    // Override of the what() method to return the error message
    inline const char* what() const noexcept override {
        return message.c_str();
    }
};

#endif // TETRISEXCEPTION_H

#ifndef NERROR_H
#define NERROR_H

#include <string>

class NError {
public:
    NError(std::string msg)
        : _msg(msg)
    {}

    const std::string &msg() {
        return _msg;
    }

private:
    std::string _msg;
};

#endif // NERROR_H

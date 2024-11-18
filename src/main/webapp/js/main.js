class InvalidValueException extends Error {
    constructor(message) {
        super(message);
        this.name = "InvalidValueException";
    }
}

function validation(values) {
    if (values.x === undefined) {
        throw new InvalidValueException('You didn\'t choose X');
    }
    if (values.r === undefined) {
        throw new InvalidValueException('You didn\'t choose R');
    }
    if (values.y === "") {
        throw new InvalidValueException('You didn\'t choose Y');
    } else {
        var floaty = parseFloat(values.y);
        if (!/^(-?\d+(\.\d+)?)$/.test(values.y)) {
            throw new InvalidValueException('Y must be the number');
        }
        if (floaty <= -5 || floaty >= 3) {
            throw new InvalidValueException('Invalid Y');
        }
    }
}


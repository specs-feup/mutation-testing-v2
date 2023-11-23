class IdNumberGenerator {

    #base
    #multiplier
    #current

    constructor(base, total) {
        this.#base = base;
        
        numDigits = (total-1).toString().length;
        if(numDigits === 0) {
            numDigits = 1
        }
        this.#multiplier = Math.pow(10, numDigits);

        Math.pow()
        this.#current = 0;
    }

    next() {
        const newId = this.#current * this.#multiplier + this.#base
        this.#current+=1
        return newId;
    }

}
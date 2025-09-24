export function randomizeArr(arr: Array<any>): Array<any> {
    let currIndex = arr.length;

    while (currIndex != 0) {
        let randomIndex = Math.floor(Math.random() * currIndex);
        currIndex--;

        [arr[currIndex], arr[randomIndex]] = [
            arr[randomIndex], arr[currIndex]
        ];
    }

    return arr;
}

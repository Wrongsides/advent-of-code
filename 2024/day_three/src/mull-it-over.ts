import * as fs from 'fs';

export class Program {

  multiply(x: number, y: number): number {
    return x * y;
  }

  clean(input: string): string[] {
    const regex = /mul\(\d{1,3},\d{1,3}\)/g;
    return input.match(regex) || [];
  }

  parse(input: string): number[] {
    const regex = /mul\((\d{1,3}),(\d{1,3})\)/;
    const match = input.match(regex) || ['0', '0'];
    return [parseInt(match[1]), parseInt(match[2])];
  }

  run(input: string): number {
    return this.clean(input)
      .map(this.parse)
      .map(i => this.multiply(i[0], i[1]))
      .reduce((a, b) => a + b, 0);
  }

  runWithCommands(input: string): number {
    const drop: string = input.replace(/don't\(\).*?do\(\)/g, '');
    const dropRest: string = drop.replace(/don't\(\).*$/, '');
    return this.run(dropRest);
  }

  runFile(filename: string): number {
    return this.run(this.readFile(filename));
  }

  runFileWithCommands(filename: string): number {
    return this.runWithCommands(this.readFile(filename));
  }

  private readFile(filename: string): string {
    const input = fs.readFileSync(process.cwd() + '/' + filename, 'utf-8');
    return input.replace(/\n/g, '')
  }
}


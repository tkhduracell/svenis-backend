
const execSync = require('child_process').execSync;

const comp = 'mvn compile';

console.log("Compiling with " + comp);
console.log(execSync(comp, {silent: true, encoding: 'utf8'}));
console.log("Compile done!");

const cmd = 'mvn exec:java -Dexec.mainClass="com.svenis.Main"';

console.log("Starting with " + cmd);
console.log(execSync(cmd, {silent: true, encoding: 'utf8'}));
console.log("Command done!");
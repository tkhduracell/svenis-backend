
const execSync = require('child_process').execSync;

const comp = 'mvn compile';

console.log("Compiling with " + comp);
code = execSync(comp);
console.log("Compile done!");

const cmd = 'mvn exec:java -Dexec.mainClass="com.svenis.Main"';

console.log("Starting with " + cmd);
code = execSync(cmd);
console.log("Command done!");
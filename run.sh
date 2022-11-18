args='';
for arg in "$@"
    do
        args="$args $arg"
    done &&
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
mvn exec:java -Dorg.slf4j.simpleLogger.defaultLogLevel=warn -Dexec.args="$args"
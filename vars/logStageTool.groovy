import groovy.json.JsonBuilder

def call(String tool, String status, def artifactUrl = "", Map metrics = [:]) {
    try {
        def logLine = [
            timestamp : new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSSZ", TimeZone.getTimeZone('UTC')),
            pipeline  : env.JOB_NAME ?: "unknown_pipeline",
            build     : env.BUILD_NUMBER ?: "unknown_build",
            stage     : env.STAGE_NAME ?: "unknown_stage",
            tool      : tool,
            status    : status,
            artifact  : artifactUrl,
            metrics   : metrics
        ]
        echo new JsonBuilder(logLine).toPrettyString()
    } catch (err) {
        echo "logStageTool ERROR: ${err}"
    }
}


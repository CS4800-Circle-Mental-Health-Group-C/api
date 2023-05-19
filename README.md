# Circle Serverless API

The [Circle API](https://github.com/CS4800-Circle-Mental-Health-Group-C/api) project, created with [`aws-serverless-java-container`](https://github.com/awslabs/aws-serverless-java-container).

This project serves as the backend for the Circle app. It is a [Serverless](https://serverless.com/) application, built with [AWS Lambda](https://aws.amazon.com/lambda/) and [Amazon API Gateway](https://aws.amazon.com/api-gateway/). It is built with [AWS SAM](https://aws.amazon.com/serverless/sam/) and [AWS CloudFormation](https://aws.amazon.com/cloudformation/). The project leverages the [`aws-serverless-java-container`](https://github.com/awslabs/aws-serverless-java-container) to run a Spring Boot 2 REST API in a Lambda function.

The project folder also includes a `template.yml` file. You can use this [SAM](https://github.com/awslabs/serverless-application-model) file to deploy the project to AWS Lambda and Amazon API Gateway or test in local with the [SAM CLI](https://github.com/awslabs/aws-sam-cli).

## Pre-requisites

- [AWS CLI](https://aws.amazon.com/cli/)
- [SAM CLI](https://github.com/awslabs/aws-sam-cli)
- [Maven](https://maven.apache.org/)
- [Java 17 - Coretto](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

## Building the project

You can use the SAM CLI to quickly build the project

```bash
$ sam build
Building codeuri: /home/camjl/repos/cs4800/api runtime: java17 metadata: {} architecture: x86_64 functions: ApiFunction
Running JavaMavenWorkflow:CopySource
Running JavaMavenWorkflow:MavenBuild
Running JavaMavenWorkflow:MavenCopyDependency
Running JavaMavenWorkflow:MavenCopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/template.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke
[*] Deploy: sam deploy --guided
```

## Testing locally with the SAM CLI

From the project root folder - where the `template.yml` file is located - start the API with the SAM CLI.

```bash
$ sam local start-api

...
Mounting com.amazonaws.serverless.archetypes.StreamLambdaHandler::handleRequest (java17) at http://127.0.0.1:3000/{proxy+} [OPTIONS GET HEAD POST PUT DELETE PATCH]
...
```

## Deploying to AWS

To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
-------------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
-------------------------------------------------------------------------------------------------------------
ApiApi - URL for application            https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/
-------------------------------------------------------------------------------------------------------------
```

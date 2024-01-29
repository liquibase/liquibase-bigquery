data "terraform_remote_state" "liquibase-bigquery-testHarnessTests" {
  backend = "remote"
  config = {
    organization = "liquibase"
    hostname     = "spacelift.io"
    workspaces = {
      name = "liquibase-bigquery-testHarnessTests"
    }
  }
}
data "terraform_remote_state" "liquibase-bigquery-testharness-tests" {
  backend = "remote"
  config = {
    organization = "liquibase"
    hostname     = "spacelift.io"
    workspaces = {
      name = "liquibase-bigquery-testharness-tests"
    }
  }
}
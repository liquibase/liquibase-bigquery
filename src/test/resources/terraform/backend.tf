
terraform {
  backend "remote" {
    organization = "liquibase"
    hostname     = "spacelift.io"
    workspaces {
      name = "liquibase-bigquery-testharness-tests"
    }
  }
}

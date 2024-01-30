
terraform {
  backend "remote" {
    organization = "liquibase"
    hostname     = "app.terraform.io"
    workspaces {
      name = "liquibase-bigquery-testharness-tests"
    }
  }
}

terraform {
  required_version = ">= 1.1.0"
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 3.90"
    }
    google-beta = {
      source  = "hashicorp/google-beta"
      version = "~> 3.90"
    }
    github = {
      source  = "integrations/github"
      version = "~> 4.0"
    }
    spacelift = {
      source  = "spacelift-io/spacelift"
      version = "1.27.0"
    }
  }
}

provider "spacelift" {}

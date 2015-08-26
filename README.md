abstract_data
============

[![Join the chat at https://gitter.im/malcolmgreaves/abstract_data](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/malcolmgreaves/abstract_data?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

A unifying typeclass describing collections and higher-order data transformation and manipulation actions common to a wide variety of data processing tasks. Inspired by the Scala collections API.

Use Cases
=========

Write an algorithm that works on a generic collection (fif.Data) and be able to instantly re-use your code to scale from local Scala collections to massively distributed Spark RDDs or Flink DataSet instances.


============
We <3 contributions! We want this code to be useful and used! We use pull requests to review and discuss changes, fixes, and improvements.

To kick off a contribution, fork this repo, make some changes on master, then submit a PR and discuss the changes. Once a consensus is reached, close the PR and manually merge onto this repository's master. 

** MAKE SURE ** you only have one commit per feature / PR. Achieve this by either doing a `git rebase` before merging or a `git merge --sqash`. The commit message should be detailed and explain everything that the commit introduces and removes.

Also, if you contriubte, add yourself to the developers list within `build.sbt`.

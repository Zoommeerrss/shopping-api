RUN echo I am using the default (/bin/sh)
RUN ["/bin/bash", "-c", "echo I am using bash"]
SHELL ["/bin/bash", "-c"]
RUN echo I am using bash, which is now the default
RUN ["/bin/sh", "-c", "echo I am using /bin/sh"]
RUN ["echo", "I don't use a shell at all"]
# Set the locale in container
RUN apt-get -y install locales
RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && \
    locale-gen
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8